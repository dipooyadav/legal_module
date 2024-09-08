package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.Entity.HearingDetails;
import com.nscs.SBMaster.repository.CaseDetailsRepository;
import com.nscs.SBMaster.service.HearingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class HearingController {

    @Autowired
    private HearingService hearingService;

    @Autowired
    private CaseDetailsRepository caseDetailsRepository;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @GetMapping("/AddHearingDetail")
    public String showCreateCaseForm(Model model) {

        model.addAttribute("hearingDetails", new HearingDetails());
        model.addAttribute("menuName", "Hearing Entry");
        model.addAttribute("contextPath", contextPath);
        return "hearing-entry";
    }


    @PostMapping("/saveHearingDetails")
    @ResponseBody
    public ResponseEntity<String> saveHearingDetails(@ModelAttribute("hearingDetails") HearingDetails hearingDetails) {
        try{
            hearingService.saveHearingDetails(hearingDetails);
            if(hearingDetails.getCaseHearingResult()!=null && hearingDetails.getCaseHearingResult().equals("C")){
                caseDetailsRepository.closeCase(hearingDetails.getCaseId());
            }
            if(hearingDetails.getCasePostDate()!=null && !hearingDetails.getCasePostDate().equals("")){
                HearingDetails posthearingDetails = new HearingDetails();
                posthearingDetails.setCaseHearingDate(hearingDetails.getCasePostDate());
                posthearingDetails.setCaseId(hearingDetails.getCaseId());
                posthearingDetails.setCaseTitle(hearingDetails.getCaseTitle());
                posthearingDetails.setCaseLawyer(hearingDetails.getCaseLawyer());
                hearingService.saveHearingDetails(posthearingDetails);
            }

            return ResponseEntity.ok("Hearing Details Saved Successfully");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error Saving Hearing Details");
        }

    }

    @GetMapping("/UpcomingHearing")
    public String showUpcomingHearing(Model model) {
        List<HearingDetails> hearings = hearingService.getAllUpcomingHearings();
        if(hearings!=null && !hearings.isEmpty()) {
            model.addAttribute("upComingHearings", hearings);
        }
        model.addAttribute("hearingDetails", new HearingDetails());
        model.addAttribute("menuName", "Upcoming Hearings");
        model.addAttribute("contextPath", contextPath);
        return "case-hearings";
    }

    @PostMapping("/updateHearingDetails")
    @ResponseBody
    public ResponseEntity<String> updateHearingDetails(@ModelAttribute("hearingDetails") HearingDetails hearingDetails, @RequestParam("attachedDocs") MultipartFile[] attachedDocs) {
        try{
            HearingDetails updated =  hearingService.updateHearingDetails(hearingDetails, attachedDocs);
            if(updated!=null && updated.getCasePostDate()!=null && !updated.getCasePostDate().equals("")){
                HearingDetails posthearingDetails = new HearingDetails();
                posthearingDetails.setCaseHearingDate(updated.getCasePostDate());
                posthearingDetails.setCaseId(updated.getCaseId());
                posthearingDetails.setCaseTitle(updated.getCaseTitle());
                posthearingDetails.setCaseLawyer(updated.getCaseLawyer());
                posthearingDetails.setCaseHeardJudges(updated.getCaseHeardJudges());
                hearingService.saveHearingDetails(posthearingDetails);
            }
            if(updated.getCaseHearingResult().equals("C")){
                return ResponseEntity.ok("Hearing Details Update and Case Closed Successfully");
            }

            return ResponseEntity.ok("Hearing Details Update Successfully");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error Updating Hearing Details");
        }

    }


    @GetMapping("/hearingHistory")
    public String hearingHistory(Model model) {
        List<HearingDetails> hearings = hearingService.getHearingHistory();
        if(hearings!=null && !hearings.isEmpty()) {
            model.addAttribute("upComingHearings", hearings);
        }
        model.addAttribute("hearingDetails", new HearingDetails());
        model.addAttribute("menuName", "Hearing History");
        model.addAttribute("contextPath", contextPath);
        return "case-hearings";
    }


}
