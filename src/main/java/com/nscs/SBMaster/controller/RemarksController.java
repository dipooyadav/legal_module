package com.nscs.SBMaster.controller;


import com.nscs.SBMaster.Entity.BidStage;
import com.nscs.SBMaster.Entity.Remarks;
import com.nscs.SBMaster.repository.RemarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Remarks")
public class RemarksController {


    @Autowired
    private RemarksRepository remarksRepository;

    @GetMapping("allRemarks")
    public List<Remarks> getremarks(){
        return remarksRepository.findAll();
    }
    @GetMapping("allRemarksFindById/{id}")
    public Optional<Remarks> getremarksById(@PathVariable("id")int id){
        return remarksRepository.findById(id);
    }

    @PostMapping("updateRemarks")
    public Remarks updateRemarks(@RequestBody Remarks updatedRemarks) {
        int id = updatedRemarks.getId(); // Extracting ID from the updated BidStage object
        Optional<Remarks> existingRemarksOptional = remarksRepository.findById(id);
        if (existingRemarksOptional.isPresent()) {
            Remarks existingRemarks = existingRemarksOptional.get();
            existingRemarks.setRemark(updatedRemarks.getRemark());
            existingRemarks.setRemarks(updatedRemarks.getRemarks());
            return remarksRepository.save(existingRemarks);
        } else {
            // Handle case where the competitor with the given ID does not exist
            throw new RuntimeException("BidStage with ID " + id + " not found");
        }
    }

   /* public Remarks updateRemarks(@RequestBody Remarks updateRemarks) {
        int id = updateRemarks.getId();//Extracting id from the Remarks object
        Optional<Remarks> existingRemarksOptional = remarksRepository.findById(id);

        if (existingRemarksOptional.isPresent()) {
            Remarks existingRemarks = existingRemarksOptional.get();
            existingRemarks.setRemark1(updateRemarks.getRemark1());
            existingRemarks.setRemark2(updateRemarks.getRemark2());
            return remarksRepository.save(existingRemarks);
        }
        else{
            // Handle case where the competitor with the given ID does not exist
            throw new RuntimeException("Remarks with ID " + id + " not found");
        }
    }*/
    @PostMapping("addRemarks")
    public List<Remarks> addRemarks(@RequestBody List<Remarks> remarksList){
        return remarksRepository.saveAll(remarksList);
    }

}

