package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.Entity.CaseDocuments;
import com.nscs.SBMaster.model.CaseDetails;
import com.nscs.SBMaster.repository.CaseDocumentsRepository;
import com.nscs.SBMaster.repository.CommonRepository;
import com.nscs.SBMaster.service.CaseDetailsService;
import com.nscs.SBMaster.service.CaseDocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class CaseController {
    @Autowired
    private CaseDetailsService caseDetailsService;

    @Autowired
    private CaseDocumentsService caseDocumentService ;

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private CaseRestController caseRestController;

    @Autowired
    private CaseDocumentsRepository caseDocumentsRepository;

    @Value("${file.upload-dir}")
    private String baseUploadDir;

    @Value("${factfile.upload-dir}")
    private String factUploadDir;

    @Value("${server.servlet.context-path}")
    private String contextPath;



    @GetMapping({"/createCase", "/createCase/{caseId}"})
    public String showCreateCaseForm(Model model, @PathVariable(required = false) String caseId) {
        model.addAttribute("contextPath", contextPath);
        if(caseId!=null){
            CaseDetails caseDetails =  caseDetailsService.getCaseById(Long.valueOf(caseId));
            model.addAttribute("caseDetails", caseDetails);
        }
        else{
            model.addAttribute("caseDetails", new CaseDetails());
        }

        List<Map<String, Object>> caseCategories = commonRepository.findAllCategories();

        model.addAttribute("caseCategories", caseCategories);

        return "create-case";
    }

    @PostMapping("/createCase")
    public String createCase(@ModelAttribute("caseDetails") CaseDetails caseDetails,Model model) {
        caseDetailsService.createCase(caseDetails);
        // Redirect to a confirmation page or another URL
        model.addAttribute("contextPath", contextPath);
        return "redirect:/createCase";
    }


    @GetMapping({"/caseList", "/caseList/{status}"})
    public String showCaseList(Model model, @PathVariable(required = false) String status) {
        model.addAttribute("contextPath", contextPath);
        List<CaseDetails> cases = null;
        if(status!=null){
            cases = caseDetailsService.getByStatus(status);
            switch (status) {
                case "R":
                    model.addAttribute("menuName", "Returned Cases");
                    break;
                case "C":
                    model.addAttribute("menuName", "Closed Cases");
                    break;
                case "A":
                    model.addAttribute("menuName", "Approved Cases");
                    break;
            }

        }
        else {
            cases = caseDetailsService.getAllCases();
            model.addAttribute("menuName", "All Cases");
        }
        for(CaseDetails caseDetail: cases){
            List<Map<String, Object>> caseCategories = commonRepository.getCategoryById(caseDetail.getCaseType());
            if(!caseCategories.isEmpty()){
                caseDetail.setCaseTypeEname(caseCategories.get(0).get("category_name").toString());
            }
        }
        model.addAttribute("cases", cases);
        List<Map<String, Object>> userList = commonRepository.getAllUsers();
        model.addAttribute("s_users", userList);
        return "case-list";
    }



    @GetMapping("/caseFact")
    public String showCaseFact(Model model) {
        List<CaseDetails> cases = caseDetailsService.getApprovedCases();
        model.addAttribute("contextPath", contextPath);
        // Remove cases based on a condition
        cases.removeIf(caseDetail -> {
            List<Map<String, Object>> caseCategories = commonRepository.getCategoryById(caseDetail.getCaseType());
            if (!caseCategories.isEmpty()) {
                caseDetail.setCaseTypeEname(caseCategories.get(0).get("category_name").toString());
            }

            if (caseDetail.getFactFileCode() != null) {
                CaseDocuments caseDocument = caseDocumentService.findByFactCode(caseDetail.getFactFileCode());
                if (caseDocument != null && caseDocument.getDoc_approval()!=null) {
                    if (caseDocument.getDoc_approval().equals("U")) {
                        caseDetail.setFactFileStatus("Unapproved");
                        return false;
                    } else if (caseDocument.getDoc_approval().equals("R")) {
                        caseDetail.setFactFileStatus("Returned");
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            return false; // Keep this case if no conditions are met
        });

        model.addAttribute("cases", cases);
        model.addAttribute("menuName", "Fact File Upload");
        return "case-fact";
    }



    @GetMapping("/CaseFactApproval")
    public String showCaseFactApproval(Model model) {
        List<CaseDetails> cases = caseDetailsService.getFactFiledCases();
        model.addAttribute("contextPath", contextPath);

        // Remove cases based on a condition
        cases.removeIf(caseDetail -> {
            List<Map<String, Object>> caseCategories = commonRepository.getCategoryById(caseDetail.getCaseType());
            if (!caseCategories.isEmpty()) {
                caseDetail.setCaseTypeEname(caseCategories.get(0).get("category_name").toString());
            }

            if (caseDetail.getFactFileCode() != null) {
                CaseDocuments caseDocument = caseDocumentService.findByFactCode(caseDetail.getFactFileCode());
                if (caseDocument != null && caseDocument.getDoc_approval()!=null) {
                    if (caseDocument.getDoc_approval().equals("U")) {
                        caseDetail.setFactFileStatus("Unapproved");
                        return false; // Keep this case
                    } else {
                        return true; // Keep this case
                    }
                }
            }
            return false; // Keep this case if no conditions are met
        });
        model.addAttribute("cases", cases);
        model.addAttribute("menuName", "Fact File Approval");
        model.addAttribute("isCaseFactApproval", true);

        return "case-fact"; // This is the Thymeleaf template name
    }

    @GetMapping("/replyDetails")
    public String showCaseReply(Model model) {
        List<CaseDetails> cases = caseDetailsService.getApprovedCasesWithNonNullFactFileCode();
        model.addAttribute("contextPath", contextPath);
        for (CaseDetails caseDetail : cases) {
            List<Map<String, Object>> caseCategories = commonRepository.getCategoryById(caseDetail.getCaseType());
            if (!caseCategories.isEmpty()) {
                caseDetail.setCaseTypeEname(caseCategories.get(0).get("category_name").toString());
            }
        }

        model.addAttribute("cases", cases);
        List<Map<String, Object>> userList = commonRepository.getAllUsers();
        model.addAttribute("screen", "Reply Details");
        return "case_reply"; // This is the Thymeleaf template name
    }


    @PostMapping("/updateCase")
    @ResponseBody
    public String updateCase(
            @ModelAttribute CaseDetails caseDetails,
            @RequestParam("supportingDocs") MultipartFile[] supportingDocs) throws IOException {

        caseDetailsService.updateCase(caseDetails);

        Long caseId = caseDetails.getId();
        String caseDir = baseUploadDir + caseId;

        // Create directory for the caseId if it doesn't exist
        File caseDirFile = new File(caseDir);
        if (!caseDirFile.exists()) {
            caseDirFile.mkdirs();
        }

        for (MultipartFile file : supportingDocs) {
            if (!file.isEmpty()) {
                // Save the file to the directory
                String filePath = caseDir + "/" + file.getOriginalFilename();
                file.transferTo(new File(filePath));

                // Save file information in the database
                CaseDocuments caseDocument = new CaseDocuments();
                caseDocument.setCaseId(caseId);
                caseDocument.setCaseDocName(file.getOriginalFilename());
                caseDocument.setCaseDocPath(filePath);
                caseDocumentsRepository.save(caseDocument);
            }
        }

        return "Case updated successfully";
    }



    @GetMapping("/returnCase/{caseId}")
    @ResponseBody
    public String returnCase(@PathVariable Long caseId) {
        try {
            CaseDetails caseDetails = caseDetailsService.getCaseById(caseId);
            if (caseDetails != null) {
                caseDetails.setCaseStatus("R");
                caseDetailsService.returnCase(caseDetails.getId());
                return "Case Returned successfully";
            } else {
                return "Case not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while returning the case";
        }
    }


    @PostMapping("/updatefeedback")
    @ResponseBody
    public ResponseEntity<String> updateFeedback(CaseDetails caseDetails) {
        System.out.println("ID: " + caseDetails.getId());
        System.out.println("Feedback: " + caseDetails.getFactFeedback());
        try {
            caseDetailsService.updateCaseFeedback(caseDetails.getId(), caseDetails.getFactFeedback());
            caseDocumentService.approveFactFile(Long.valueOf(caseDetails.getFactFileCode()));
            return ResponseEntity.ok("Fact File Approved Successfully");
        } catch (RuntimeException e) {
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occurred : " + e.getMessage());

        }
    }


    @PostMapping("/updateFact")
    @ResponseBody
    public String updateFact(@ModelAttribute CaseDetails caseDetails, @RequestParam("supportingDocs") MultipartFile[] supportingDocs) {
        try {
            Long caseId = caseDetails.getId();
            String caseDir = factUploadDir + caseId;

            // Create directory for the caseId if it doesn't exist
            File caseDirFile = new File(caseDir);
            if (!caseDirFile.exists()) {
                caseDirFile.mkdirs();
            }
            for (MultipartFile file : supportingDocs) {
                if (!file.isEmpty()) {

                    String filePath = caseDir + "/" + file.getOriginalFilename();
                    file.transferTo(new File(filePath));

                    // Save file information in the database
                    CaseDocuments caseDocument = new CaseDocuments();
                    caseDocument.setCaseId(caseId);
                    caseDocument.setCaseDocName(file.getOriginalFilename());
                    caseDocument.setCaseDocPath(filePath);
                    caseDocument.setDocType("Fact");
                    caseDocument.setDoc_approval("U");
                    caseDocumentsRepository.save(caseDocument);
                    if(caseDocument.getDocId()!=null)
                        caseDetailsService.updateFactFile(caseId, String.valueOf(caseDocument.getDocId()));
                }
            }
            return "Documents uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading documents. Please try again.";
        }
    }


    @PostMapping(path = "/returnFactFile")
    @ResponseBody
    public ResponseEntity<String> returnFactFile(@ModelAttribute CaseDetails caseDetails){
        try{
            if(caseDetails.getId()!=null && caseDetails.getFactFileCode()!=null){
                caseDetailsService.updateCaseFeedback(caseDetails.getId(), caseDetails.getFactFeedback());
                caseDocumentService.returnFactFile(Long.valueOf(caseDetails.getFactFileCode()));
                return ResponseEntity.ok("Fact File Returned Successfully");
            }
            else {
                return ResponseEntity.badRequest().body("Error Returning Fact File: Missing ID or FactFileCode");
            }

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Returning Fact File: " + e.getMessage());
        }
    }

    @PostMapping("/updateFactReply")
    @ResponseBody
    public String updateFactReply(@ModelAttribute CaseDetails caseDetails, @RequestParam("receiptDocs") MultipartFile[] receiptDocs) {
        try {
            Long caseId = caseDetails.getId();
            String caseDir = factUploadDir + caseId;

            // Create directory for the caseId if it doesn't exist
            File caseDirFile = new File(caseDir);
            if (!caseDirFile.exists()) {
                caseDirFile.mkdirs();
            }
            for (MultipartFile file : receiptDocs) {
                if (!file.isEmpty()) {

                    String filePath = caseDir + "/" + file.getOriginalFilename();
                    file.transferTo(new File(filePath));

                    // Save file information in the database
                    CaseDocuments caseDocument = new CaseDocuments();
                    caseDocument.setCaseId(caseId);
                    caseDocument.setCaseDocName(file.getOriginalFilename());
                    caseDocument.setCaseDocPath(filePath);
                    caseDocument.setDocType("Fact");
                    caseDocumentsRepository.save(caseDocument);
                    if(caseDocument.getDocId()!=null)
                        caseDetailsService.updateFactFile(caseId, String.valueOf(caseDocument.getDocId()));
                }
            }
            return "Documents uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading documents. Please try again.";
        }
    }

    @DeleteMapping("/deleteCase/{caseId}")
    public ResponseEntity<Void> deleteCase(@PathVariable Long caseId) {
        try {
            caseDetailsService.deleteCaseById(caseId);
            return ResponseEntity.noContent().build();  // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }
}
