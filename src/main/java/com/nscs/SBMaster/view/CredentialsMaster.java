package com.nscs.SBMaster.view;


import com.nscs.SBMaster.Entity.BidStage;
import com.nscs.SBMaster.Entity.Credentials;
import com.nscs.SBMaster.repository.BidStageRepository;
import com.nscs.SBMaster.repository.CredentialsRepository;
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
public class CredentialsMaster {


    @Autowired
    private CredentialsRepository credentialsRepository;

    @GetMapping("/CredentialsIndex")
    public String showUserList(Model model){
        List<Credentials> credentials = credentialsRepository.findAll();
        model.addAttribute( "credentials",credentials);
        return "Credentials_All";
    }

    @GetMapping("/CredentialsSignup")
    public String showSignUpForm(@Valid Credentials credentials, BindingResult result, Model model) {
        model.addAttribute("credentials", credentials);
        //return "add_bid_stage";
        if (result.hasErrors()) {
            return "credentials_add";
        }
        return "credentials_add";
    }


    @PostMapping("/addCredentials")
    public String addCredentials(@Valid Credentials credentials, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Credentials_All";
        }

        credentialsRepository.save(credentials);
        return "redirect:/CredentialsIndex";
    }



    @GetMapping("/editCredentialsById/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Credentials credentials = credentialsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("credentials", credentials);

        return "Credentials_update";
    }

    @PostMapping("/updateCredentialsByid/{id}")
    public String updateCredentials(@PathVariable("id") Integer id, @Valid Credentials credentials, BindingResult result, Model model) {
        if (result.hasErrors()) {
            credentials.setId(id);
            return "Credentials_update";
        }

        credentialsRepository.save(credentials);

        return "redirect:/CredentialsIndex";
    }





}
