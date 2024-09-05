package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.Entity.BidStage;
import com.nscs.SBMaster.repository.BidStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("BidStage")
public class BidStageController {

        @Autowired
        private BidStageRepository bidStageRepository;
    @GetMapping("/allBidStage")

        public List<BidStage> getbidStage() {
            return bidStageRepository.findAll();
        }

        @GetMapping("/allBidStageById/{id}")
        public Optional<BidStage> getbidStageById(@PathVariable("id") int id) {
            return bidStageRepository.findById(id);
        }



    @PostMapping("/updateBidStageName")
    public BidStage updateBidStage(@RequestBody BidStage updatedBidStage) {
        int id = updatedBidStage.getId(); // Extracting ID from the updated BidStage object
        Optional<BidStage> existingBidStageOptional = bidStageRepository.findById(id);
        if (existingBidStageOptional.isPresent()) {
            BidStage existingBidStage = existingBidStageOptional.get();
            existingBidStage.setName(updatedBidStage.getName());
            existingBidStage.setDescription(updatedBidStage.getDescription());
            return bidStageRepository.save(existingBidStage);
        } else {
            // Handle case where the competitor with the given ID does not exist
            throw new RuntimeException("BidStage with ID " + id + " not found");
        }
    }

    @PostMapping("/addBidStag")
    public List<BidStage> addBidStage(@RequestBody List<BidStage> bidStageList) {
        // You may want to add validation logic here before saving
        return bidStageRepository.saveAll(bidStageList);
    }

}


