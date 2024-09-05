package com.nscs.SBMaster.view;


import com.nscs.SBMaster.Entity.BidStage;
import com.nscs.SBMaster.repository.BidStageRepository;
import com.nscs.SBMaster.repository.RegionRepository;
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
public class BidStageMaster {


    @Autowired
    private BidStageRepository bidStageRepository;

    @GetMapping("/BidStageIndex")
    public String showUserList(Model model){
        List<BidStage> bidStage = bidStageRepository.findAll();
        model.addAttribute( "bidstage",bidStage);
        return "BidStageAll";
    }

        @GetMapping("/AllIndex")
        public  String allIndex(Model model){
         return "Index_All";
        }
    @GetMapping("/BidStageSignup")
    public String showSignUpForm(@Valid BidStage bidStage,BindingResult result,Model model) {
        model.addAttribute("bidStage", bidStage);
        //return "add_bid_stage";
        if (result.hasErrors()) {
            return "bid_stage_add";
        }
        return "bid_stage_add";
    }


    @PostMapping("/addBidStage")
    public String addBidStage(@Valid BidStage bidStage, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "BidStageAll";
        }

        bidStageRepository.save(bidStage);
        return "redirect:/BidStageIndex";
    }



    @GetMapping("/editBidStageBYid/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidStage bidSt = bidStageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("bidSt", bidSt);

        return "BidStage_update";
    }

        @PostMapping("/updateBidStageByid/{id}")
        public String updateBidStage(@PathVariable("id") Integer id, @Valid BidStage bidStage, BindingResult result, Model model) {
            if (result.hasErrors()) {
                bidStage.setId(id);
                return "BidStage_update";
            }

            bidStageRepository.save(bidStage);

            return "redirect:/BidStageIndex";
        }




}
