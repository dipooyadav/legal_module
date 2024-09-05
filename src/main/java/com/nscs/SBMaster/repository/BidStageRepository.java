package com.nscs.SBMaster.repository;

import com.nscs.SBMaster.Entity.BidStage;
import com.nscs.SBMaster.Entity.Competitors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidStageRepository extends JpaRepository<BidStage,Integer> {
}
