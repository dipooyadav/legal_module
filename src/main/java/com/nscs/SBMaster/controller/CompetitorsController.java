package com.nscs.SBMaster.controller;


import com.nscs.SBMaster.Entity.Competitors;
import com.nscs.SBMaster.repository.CompetitorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("competitors")
public class CompetitorsController {

    @Autowired
    private CompetitorsRepository competitorsRepository;

    @GetMapping("/allcompetitors")
    public List<Competitors> getcompetitors() {
        return competitorsRepository.findAll() ;
    }

    @GetMapping("/allcompetitorsById/{id}")
    public Optional<Competitors> getcompetitorsById(@PathVariable("id") int id) {
        return competitorsRepository.findById(id) ;
    }

    @PostMapping("/updateCompetitorsName")
    public Competitors updateCompetitor(@RequestBody Competitors updatedCompetitor) {
        int id = updatedCompetitor.getId(); // Extracting ID from the updated competitor object
        Optional<Competitors> existingCompetitorOptional = competitorsRepository.findById(id);

        if (existingCompetitorOptional.isPresent()) {
            Competitors existingCompetitor = existingCompetitorOptional.get();
            existingCompetitor.setName(updatedCompetitor.getName());
            return competitorsRepository.save(existingCompetitor);
        } else {
            // Handle case where the competitor with the given ID does not exist
            throw new RuntimeException("Competitor with ID " + id + " not found");
        }
    }

    @PostMapping("/addCompetitors")
    public List<Competitors> addCompetitors(@RequestBody List<Competitors> competitorsList) {
        // You may want to add validation logic here before saving
        return competitorsRepository.saveAll(competitorsList);
    }




}
