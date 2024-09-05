package com.nscs.SBMaster.view;


import com.nscs.SBMaster.Entity.Vertical;
import com.nscs.SBMaster.repository.VerticalRepository;
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
public class VerticalMaster {
    @Autowired
    private VerticalRepository verticalRepository;

    @GetMapping("/VerticaIndex")
    public String showUserList(Model model){
        List<Vertical> vertical = verticalRepository.findAll();
        model.addAttribute( "vert",vertical);
        return "Vertical_All";
    }

    @GetMapping("/VerticalSignup")
    public String showSignUpForm(@Valid Vertical vertical,BindingResult result,Model model) {
        model.addAttribute("vert", vertical);
        //return "add_bid_stage";
        if (result.hasErrors()) {
            return "vertical_add";
        }
        return "vertical_add";
    }

    @PostMapping("/addVertical")
    public String addBidStage(@Valid Vertical vertical, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Vertical_All";
        }

        verticalRepository.save(vertical);
        return "redirect:/VerticaIndex";
    }

    @GetMapping("/editVerticalByid/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Vertical vertical = verticalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("vertical", vertical);

        return "vertical_update";
    }

    @PostMapping("/updateVerticalByid/{id}")
    public String updateBidStage(@PathVariable("id") Integer id, @Valid Vertical vertical, BindingResult result, Model model) {
        if (result.hasErrors()) {
            vertical.setId(id);
            return "vertical_update";
        }

        verticalRepository.save(vertical);

        return "redirect:/VerticaIndex";
    }



}
