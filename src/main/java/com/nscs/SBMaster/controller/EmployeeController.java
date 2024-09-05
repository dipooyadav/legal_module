package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.model.Employee;
import com.nscs.SBMaster.repository.EmployeeRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository ;

    @GetMapping("/allemployee")
    public List<Employee> getAllEmployee() {
     return employeeRepository.findAll() ;
    }




    @GetMapping("/getemployee/{id}")
    public Employee showUpdateForm(@PathVariable("id") long id) {
        Employee user = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        return user;




    }



}
