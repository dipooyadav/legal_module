package com.nscs.SBMaster.view;


import com.nscs.SBMaster.Entity.BidStage;
import com.nscs.SBMaster.Entity.Remarks;
import com.nscs.SBMaster.repository.BidStageRepository;
import com.nscs.SBMaster.repository.RemarksRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RemarksMaster {

    @Autowired
    private RemarksRepository remarksRepository;

    @GetMapping("/RemarksIndex")
    public String showUserList(Model model){
        List<Remarks> rema = remarksRepository.findAll();
        model.addAttribute( "rema",rema);
        return "Remarks_All";
    }

    @GetMapping("/RemarksSignup")
    public String showSignUpForm(@Valid Remarks rema, BindingResult result, Model model) {
        model.addAttribute("rema", rema);
        //return "add_bid_stage";
        if (result.hasErrors()) {
            return "Remarks_add";
        }
        return "Remarks_add";
    }

    @PostMapping("/addRems")
    public String addRemarks(@Valid Remarks rema, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Remarks_All";
        }

        remarksRepository.save(rema);
        return "redirect:/RemarksIndex";
    }

    @GetMapping("/editRemarksById/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Remarks rema = remarksRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("rema", rema);

        return "Remarks_update";
    }

    @PostMapping("/updateRemarksByid/{id}")
    public String updateRemarks(@PathVariable("id") Integer id, @Valid Remarks rema, BindingResult result, Model model) {
        if (result.hasErrors()) {
            rema.setId(id);
            return "Remarks_update";
        }

        remarksRepository.save(rema);

        return "redirect:/RemarksIndex";
    }

}
