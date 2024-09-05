package com.nscs.SBMaster.view;

import com.nscs.SBMaster.model.Maintenance;
import com.nscs.SBMaster.repository.MaintenanceRepository;
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

public class MaintController {

        @Autowired
        private MaintenanceRepository userRepository;


        @GetMapping("/Maintlist")
        public String showUserList(HttpServletRequest request, Model model) {
            List<Maintenance> emp = userRepository.findAll() ;
            model.addAttribute("users", emp);
            return   "MaintList";
        }

        @GetMapping("/MaintEntry")
        public String showSignUpForm(Maintenance user,Model model) {
            model.addAttribute("user", user);
            return "MaintEntry";
        }

        @PostMapping("/Maintadd")
        public String addUser(@Valid Maintenance user, BindingResult result, Model model) {
            if (result.hasErrors()) {
                return "MaintEntry";
            }

            userRepository.save(user);
            return "redirect:/Maintlist";
        }

        @GetMapping("/MaintEdit/{id}")
        public String showUpdateForm(@PathVariable("id") long id, Model model) {
            Maintenance user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            model.addAttribute("user", user);

            return "TenderUpdate";
        }

        @PostMapping("/MaintDelete/{id}")
        public String updateUser(@PathVariable("id") long id, @Valid Maintenance user, BindingResult result, Model model) {
            if (result.hasErrors()) {
                user.setId(id);
                return "TenderUpdate";
            }

            userRepository.save(user);

            return "redirect:/Maintlist";
        }


    }


