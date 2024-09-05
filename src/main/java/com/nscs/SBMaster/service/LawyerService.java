package com.nscs.SBMaster.service;

import com.nscs.SBMaster.model.LawyerDetails;
import com.nscs.SBMaster.repository.LawyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LawyerService {
    @Autowired
    private LawyerRepository lawyerRepository;

    public LawyerDetails saveLawyer(LawyerDetails lawyer) {
        return lawyerRepository.save(lawyer);
    }
}