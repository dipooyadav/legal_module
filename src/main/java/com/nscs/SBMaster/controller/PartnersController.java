package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.Entity.Partners;
import com.nscs.SBMaster.repository.PartnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("partners")
public class PartnersController {

    @Autowired
    private PartnersRepository partnersRepository;

    @GetMapping("/allpartners")
    public List<Partners> getpartners() {
        return partnersRepository.findAll() ;
    }

    @GetMapping("/allpartnersById/{id}")
    public Optional<Partners> getpartnersById(@PathVariable("id") int id) {
        return partnersRepository.findById(id) ;
    }

    @PostMapping("/updatePartnersName")
    public Partners updatePartners(@RequestBody Partners updatedPartners) {
        int id = updatedPartners.getId(); // Extracting ID from the updated partners object
        Optional<Partners> existingPartnersOptional = partnersRepository.findById(id);
        if (existingPartnersOptional.isPresent()) {
            Partners existingPartners = existingPartnersOptional.get();
            existingPartners.setName(updatedPartners.getName());
            existingPartners.setPartnershipTypes_id(updatedPartners.getPartnershipTypes_id());
            return partnersRepository.save(existingPartners);
        } else {
            // Handle case where the competitor with the given ID does not exist
            throw new RuntimeException("Partners with ID " + id + " not found");
        }
    }

    @PostMapping("/addPartners")
    public List<Partners> addPartners(@RequestBody List<Partners> partnersList) {
        // You may want to add validation logic here before saving
        return partnersRepository.saveAll(partnersList);
    }

}
