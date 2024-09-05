package com.nscs.SBMaster.view;

import com.nscs.SBMaster.Entity.Partners;
import com.nscs.SBMaster.repository.PartnersRepository;
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
public class PartnersMaster {


    @Autowired
    private PartnersRepository partnersRepository;


    @GetMapping("/PartnersIndex")
    public String showUserList(Model model) {
        List<Partners> partners = partnersRepository.findAll();
        model.addAttribute("partners", partners);
        return "Partners_All";
    }

    @GetMapping("/PartnersSignup")
    public String showSignUpForm(@Valid Partners partners, BindingResult result, Model model) {
        model.addAttribute("partners", partners);
        //return "add_bid_stage";
        if (result.hasErrors()) {
            return "partners_add";
        }
        return "partners_add";
    }

    @PostMapping("/addPartners")
    public String addBidStage(@Valid Partners partners, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Partners_All";
        }
        partnersRepository.save(partners);
        return "redirect:/PartnersIndex";

    }

    @GetMapping("/editPartnersById/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Partners partners = partnersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("partners", partners);

        return "Partners_update";
    }

    @PostMapping("/updatePartnersByid/{id}")
    public String updateBidStage(@PathVariable("id") Integer id, @Valid Partners partners, BindingResult result, Model model) {
        if (result.hasErrors()) {
            partners.setId(id);
            return "Partners_update";
        }

        partnersRepository.save(partners);

        return "redirect:/PartnersIndex";
    }

}