package com.nscs.SBMaster.view;

import com.nscs.SBMaster.Entity.CustomerConnects;
import com.nscs.SBMaster.repository.CustomerConnectsRepository;
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
public class CustomerConnectsMaster {
    @Autowired
    private CustomerConnectsRepository customerConnectsRepository;

    @GetMapping("/CustomerConnectsIndex")
    public String showCompetitorsList(Model model){
        List<CustomerConnects> connect = customerConnectsRepository.findAll();
        model.addAttribute("connects",connect);
        return "CustomerConnectsAll";
    }

    @GetMapping("/CustomerConnectsSignup")
    public String showSignUpForm(@Valid CustomerConnects customer, BindingResult result, Model model) {
        model.addAttribute("customer", customer);
        //return "add_bid_stage";
        if (result.hasErrors()) {
            return "Customer_add";
        }
        return "Customer_add";
    }

    @PostMapping("/addCustomer")
    public String addBidStage(@Valid CustomerConnects connects, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "CustomerConnectsAll";
        }

        customerConnectsRepository.save(connects);
        return "redirect:/CustomerConnectsIndex";
    }
    @GetMapping("/editCustomerById/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CustomerConnects cc = customerConnectsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("cc", cc);

        return "customer_update";
    }

    @PostMapping("/updateCustomerById/{id}")
    public String updateBidStage(@PathVariable("id") Integer id, @Valid CustomerConnects cc, BindingResult result, Model model) {
        if (result.hasErrors()) {
            cc.setId(id);
            return "customer_update";
        }

        customerConnectsRepository.save(cc);

        return "redirect:/CustomerConnectsIndex";
    }


}
