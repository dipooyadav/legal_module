package com.nscs.SBMaster.view;
import jakarta.validation.Valid;

import com.nscs.SBMaster.model.Employee;
import com.nscs.SBMaster.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class EmployeeMaster {
    @Autowired
    private  EmployeeRepository userRepository;


    @GetMapping("/index")
    public String showUserList(Model model) {
        List<Employee> emp = userRepository.findAll() ;
        model.addAttribute("users", emp);
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Employee user,Model model) {
        model.addAttribute("user", user);
        return "add_user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid Employee user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Employee user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);

        return "update_user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Employee user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update_user";
        }

        userRepository.save(user);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Employee user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);

        return "redirect:/index";
    }
}
