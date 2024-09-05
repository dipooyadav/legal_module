package com.nscs.SBMaster.view;


import com.nscs.SBMaster.Entity.Competitors;
import com.nscs.SBMaster.repository.CompetitorsRepository;
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
public class CompetitorsMaster {

    @Autowired
    CompetitorsRepository competitorsRepository;

    @GetMapping("/CompetitorsIndex")
    public String showCompetitorsList(Model model){
        List<Competitors> com = competitorsRepository.findAll();
        model.addAttribute("comp",com);
        return "CompetitorAll";
    }


    @GetMapping("/CompetitorsSignup")
    public String showSignUpForm(@Valid Competitors competitors, BindingResult result, Model model) {
        model.addAttribute("competitors", competitors);
        //return "add_bid_stage";
        if (result.hasErrors()) {
            return "Competitors_add";
        }
        return "Competitors_add";
    }

        @PostMapping("/addCompetitors")
        public String addCompetitors(@Valid Competitors competitors, BindingResult result,Model model){
            if(result.hasErrors()){
                return "CompetitorAll";
            }
            competitorsRepository.save(competitors);
            return "redirect:/CompetitorsIndex";
        }

    @GetMapping("/editCompetitorsById/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Competitors competitors = competitorsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("copetitors", competitors);

        return "Competetor_update";
    }

    @PostMapping("/updateCompetitorsById/{id}")
    public String updateBidStage(@PathVariable("id") Integer id, @Valid Competitors compet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            compet.setId(id);
            return "Competetor_update";
        }

        competitorsRepository.save(compet);

        return "redirect:/CompetitorsIndex";
    }





}
