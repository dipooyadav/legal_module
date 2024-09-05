package com.nscs.SBMaster.view;

import com.nscs.SBMaster.model.Tender;
import com.nscs.SBMaster.repository.TenderRepository;
import jakarta.servlet.http.HttpServletRequest;
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

public class TenderMaster {

        @Autowired
        private TenderRepository userRepository;


        @GetMapping("/tenderlist")
        public String showUserList(HttpServletRequest request, Model model) {
            List<Tender> emp = userRepository.findAll() ;
            model.addAttribute("users", emp);
            return   "/Master/"+"tenderList";
        }

        @GetMapping("/tenderEntry")
        public String showSignUpForm(Tender user,Model model) {
            model.addAttribute("user", user);
            return "tenderEntry";
        }

        @PostMapping("/addtender")
        public String addUser(@Valid Tender user, BindingResult result, Model model) {
            if (result.hasErrors()) {
                return "tenderEntry";
            }

            userRepository.save(user);
            return "redirect:/tenderlist";
        }

        @GetMapping("/tenderEdit/{id}")
        public String showUpdateForm(@PathVariable("id") long id, Model model) {
            Tender user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            model.addAttribute("user", user);

            return "TenderUpdate";
        }

        @PostMapping("/updateTender/{id}")
        public String updateUser(@PathVariable("id") long id, @Valid Tender user, BindingResult result, Model model) {
            if (result.hasErrors()) {
                user.setId(id);
                return "TenderUpdate";
            }

            userRepository.save(user);

            return "redirect:/tenderlist";
        }


    }

