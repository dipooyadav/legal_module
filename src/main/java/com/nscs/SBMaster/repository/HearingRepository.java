package com.nscs.SBMaster.repository;

import com.nscs.SBMaster.Entity.HearingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HearingRepository extends JpaRepository<HearingDetails, Long> {
    @Query(value = "SELECT * FROM case_hearing_details WHERE CONVERT(DATE, case_hearing_date, 103) >= CAST(GETDATE() AS DATE) and case_hearing_result is null;",
            nativeQuery = true)
    List<HearingDetails> findUpcomingHearings();


    @Query(value = "SELECT * FROM case_hearing_details WHERE CONVERT(DATE, case_hearing_date, 103) <= CAST(GETDATE() AS DATE) or case_hearing_result is not null;",
            nativeQuery = true)
    List<HearingDetails> findHearingHistory();

}
