package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.model.LawyerDetails;
import com.nscs.SBMaster.service.LawyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/lawyer")
public class LawyerController {
    @Autowired
    private LawyerService lawyerService;

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("lawyer", new LawyerDetails());
        return "lawyer-details";
    }

    @PostMapping("/save")
    public String saveLawyer(LawyerDetails lawyerDetails, @RequestParam("photo") MultipartFile photo, Model model) {
        try {
            if (!photo.isEmpty()) {
                lawyerDetails.setPhoto(photo.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload photo");
            return "error";
        }
        lawyerService.saveLawyer(lawyerDetails);
        return "redirect:/lawyer/form";
    }
}
