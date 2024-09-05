package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.Entity.CaseDocuments;
import com.nscs.SBMaster.model.CaseDetails;
import com.nscs.SBMaster.model.ReplyDetails;
import com.nscs.SBMaster.repository.CaseDetailsRepository;
import com.nscs.SBMaster.repository.CaseDocumentsRepository;
import com.nscs.SBMaster.repository.CommonRepository;
import com.nscs.SBMaster.service.CaseDetailsService;
import com.nscs.SBMaster.service.CaseDocumentsService;
import com.nscs.SBMaster.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
public class CaseRestController {
    @Autowired
    private CaseDocumentsRepository caseDocumentsRepository;

    @Autowired
    private CaseDetailsService caseDetailsService;

    @Autowired
    private CaseDocumentsService caseDocumentsService;

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private CaseDetailsRepository caseDetailsRepository;


    @Value("${factfile.upload-dir}")
    private String factUploadDir;
    @Value("${complianceMark.upload-dir}")
    private String complianceMarkDir;


    @PostMapping("/save")
    public String saveReplyDetails(@ModelAttribute ReplyDetails replyDetails, @RequestParam("receiptDocs") MultipartFile[] receiptDocs) {
        try {
            Long caseId = replyDetails.getCaseId();
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
                    caseDocument.setDocType("Reply");
                    caseDocumentsRepository.save(caseDocument);
                    if (caseDocument.getDocId() != null)
                        caseDetailsService.updateFactFile(caseId, String.valueOf(caseDocument.getDocId()));
                }
            }
            caseDocumentsService.saveReplyDetails(replyDetails);
            return "Reply Saved Successfully";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Error Saving Data";
        }
    }

    @GetMapping(value = "/getCaseDetail/{caseId}", produces = "application/json")
    public ResponseEntity<CaseDetails> getCaseDetail(@PathVariable Long caseId) {
        CaseDetails caseDetail = caseDetailsService.getCaseById(caseId);
        if (caseDetail != null) {
            List<Map<String, Object>> caseCategories = commonRepository.getCategoryById(caseDetail.getCaseType());
            caseDetail.setCaseTypeEname(caseCategories.get(0).get("category_name").toString());
            if(caseDetail.getAssignedTo()!=null && !Objects.equals(caseDetail.getAssignedTo(), "")) {
                List<Map<String, Object>> userDetail = commonRepository.getUserById(Long.valueOf(caseDetail.getAssignedTo()));
                if (!userDetail.isEmpty()) {
                    caseDetail.setAssignedToEname((String) userDetail.get(0).get("usr_ename"));
                }
            }
            return ResponseEntity.ok(caseDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(value = "/getUserDetail/{userId}", produces = "application/json")
    public ResponseEntity<Object> getUserDetail(@PathVariable Long userId) {
        // Check if the userId is null
        if (userId == null) {
            return ResponseEntity.badRequest().body(createResponse("0", "User ID is null"));
        }

        // Fetch the user details from the repository
        List<Map<String, Object>> userDetail = commonRepository.getUserById(userId);
        if (userDetail == null || userDetail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createResponse("0", "User not found"));
        }

        // Return the user details if found
        return ResponseEntity.ok(userDetail);
    }

    // Helper method to create a response map
    private Map<String, String> createResponse(String status, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("Status", status);
        response.put("Message", message);
        return response;
    }




    @GetMapping(value = "/getDocumentDetails/{docId}", produces = "application/json")
    public ResponseEntity<Optional<CaseDocuments>> getDocumentDetails(@PathVariable Long docId) {
        Optional<CaseDocuments> caseDocument = caseDocumentsRepository.findById(docId);
        if (caseDocument.isPresent()) {
            return ResponseEntity.ok(caseDocument);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/searchCaseDetails")
    public List<Map<String, Object>> searchCaseDetails(@RequestParam String query) {
        return caseDetailsService.searchCaseDetails(query);
    }


    @GetMapping("/searchAssupmstDetails")
    public List<Map<String, Object>> searchAssupmstDetails(@RequestParam String query) {
        return commonService.searchAssupmstDetails(query);
    }
    @GetMapping(value="/getDoc/{id}",produces = "application/json")
    public CaseDocuments getDocumentById(@PathVariable Long id) {
        return caseDocumentsService.getDocumentById(id);
    }




    @PostMapping("/caseComplianceMark")
    public ResponseEntity<String> markCompliance(
            @RequestParam("caseCompId") String caseId,
            @RequestParam("compCheck") boolean isMarked,
            @RequestParam("supportingDocs") MultipartFile[] supportingDocs) {

        Long caseIdLong = Long.valueOf(caseId);
        CaseDetails caseDetails = caseDetailsService.getCaseById(caseIdLong);

        if (caseDetails == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the compliance flag based on the checkbox value
        caseDetails.setCompFlag(isMarked ? "Y" : "N");
        caseDetailsRepository.save(caseDetails);

        System.out.println("Compliance " + (isMarked ? "is" : "is not") + " marked for caseId: " + caseId);

        // Handle supporting documents if any
        if (supportingDocs != null && supportingDocs.length > 0) {
            String caseDir = complianceMarkDir + caseId;
            File caseDirFile = new File(caseDir);
            if (!caseDirFile.exists()) {
                caseDirFile.mkdirs();
            }

            for (MultipartFile file : supportingDocs) {
                if (!file.isEmpty()) {
                    String filePath = caseDir + "/" + file.getOriginalFilename();
                    try {
                        file.transferTo(new File(filePath));
                    } catch (IOException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error saving file: " + file.getOriginalFilename());
                    }

                    // Save file information in the database
                    CaseDocuments caseDocument = new CaseDocuments();
                    caseDocument.setCaseId(caseIdLong);
                    caseDocument.setCaseDocName(file.getOriginalFilename());
                    caseDocument.setCaseDocPath(filePath);
                    caseDocument.setDocType("Comp");
                    caseDocumentsRepository.save(caseDocument);
                }
            }
        }

        return ResponseEntity.ok("Compliance Marked successfully!");
    }



}
