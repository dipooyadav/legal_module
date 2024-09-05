package com.nscs.SBMaster.controller;


import com.nscs.SBMaster.Entity.Competitors;
import com.nscs.SBMaster.Entity.CustomerConnects;
import com.nscs.SBMaster.repository.CompetitorsRepository;
import com.nscs.SBMaster.repository.CustomerConnectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customerConnects")
public class CustomerConnectsController {

    @Autowired
    private CustomerConnectsRepository customerConnectsRepository;

    @GetMapping("/allcustomerConnects")
    public List<CustomerConnects> getcustomerConnects() {
        return customerConnectsRepository.findAll() ;
    }

    @GetMapping("/allcustomerConnectsById/{id}")
    public Optional<CustomerConnects> getcustomerConnectsById(@PathVariable("id") int id) {
        return customerConnectsRepository.findById(id) ;
    }

    @PostMapping("/updateCustomerConnectsName")
    public CustomerConnects updateCustomerConnects(@RequestBody CustomerConnects updatedCustomerConnects) {
        int id = updatedCustomerConnects.getId(); // Extracting ID from the updated competitor object
        Optional<CustomerConnects> existingCustomerConnectsOptional = customerConnectsRepository.findById(id);

        if (existingCustomerConnectsOptional.isPresent()) {
            CustomerConnects existingCustomerConnects = existingCustomerConnectsOptional.get();
            existingCustomerConnects.setName(updatedCustomerConnects.getName());
            existingCustomerConnects.setDescription(updatedCustomerConnects.getDescription());
            return customerConnectsRepository.save(existingCustomerConnects);
        } else {
            // Handle case where the competitor with the given ID does not exist
            throw new RuntimeException("CustomerConnects with ID " + id + " not found");
        }
    }

    @PostMapping("/addCustomerConnects")
    public List<CustomerConnects> addCustomerConnects(@RequestBody List<CustomerConnects> customerConnectsList) {
        // You may want to add validation logic here before saving
        return customerConnectsRepository.saveAll(customerConnectsList);
    }
}
