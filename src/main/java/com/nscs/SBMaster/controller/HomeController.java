package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.Entity.User;
import com.nscs.SBMaster.service.UserService;
import com.nscs.SBMaster.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private UserRepo userRepo;


    @Autowired
    private UserService userService;


    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }



    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session){

       // System.out.println(user);

       User u = userService.saveUser(user);

       if(u!=null){
          // System.out.println("secussfull");

           session.setAttribute("msg","Registration successful");
       }else {
         //  System.out.println("error");
           session.setAttribute("msg","Somethin wrong ");
       }

        return "redirect:/register";
    }



}
