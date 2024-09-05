package com.nscs.SBMaster.controller;


import com.nscs.SBMaster.Entity.Vertical;
import com.nscs.SBMaster.repository.VerticalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(name="verticals")
public class VerticalController {

    @Autowired
    private VerticalRepository verticalRepository;

    @GetMapping("/allverticals")
    public List<Vertical> getAllVertical() {
        return verticalRepository.findAll() ;
    }

    @GetMapping("/allverticalsById/{id}")
    public Optional<Vertical> getverticalsById(@PathVariable("id") int id) {
        return verticalRepository.findById(id) ;
    }

    @PostMapping("/updateVerticalsName")
    public Vertical updateVerticals(@RequestBody Vertical updatedVerticals) {
        int id = updatedVerticals.getId(); // Extracting ID from the updated competitor object
        Optional<Vertical> existingVerticalOptional = verticalRepository.findById(id);
        if (existingVerticalOptional.isPresent()) {
            Vertical existingVertical = existingVerticalOptional.get();
            existingVertical.setId(updatedVerticals.getId());
            existingVertical.setVertical_name(updatedVerticals.getVertical_name());
            return verticalRepository.save(existingVertical);
        } else {
            // Handle case where the competitor with the given ID does not exist
            throw new RuntimeException("Competitor with ID " + id + " not found");
        }
    }

    @PostMapping("/addVerticals")
    public List<Vertical> addVerticals(@RequestBody List<Vertical> verticalsList) {
        // You may want to add validation logic here before saving
        return verticalRepository.saveAll(verticalsList);
    }



}
