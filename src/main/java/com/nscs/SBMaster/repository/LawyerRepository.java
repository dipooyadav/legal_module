package com.nscs.SBMaster.repository;


import com.nscs.SBMaster.model.LawyerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawyerRepository extends JpaRepository<LawyerDetails, Long> {

}

