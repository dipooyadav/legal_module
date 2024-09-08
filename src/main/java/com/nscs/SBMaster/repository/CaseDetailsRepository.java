package com.nscs.SBMaster.repository;

import com.nscs.SBMaster.model.CaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CaseDetailsRepository extends JpaRepository<CaseDetails, Long> {

    @Query(value = "Select * from case_details where case_status='A'",
            nativeQuery = true)
    List<CaseDetails> findApprovedCases();

    @Query(value = "Select * from case_details where case_status=:status",
            nativeQuery = true)
    List<CaseDetails> findByStatus(String status);

    @Query(value = "Select * from case_details where case_status=:status and comp_flag is null",
            nativeQuery = true)
    List<CaseDetails> findClosed(String status);



    @Query(value = "Select * from case_details where id=:id",
            nativeQuery = true)
    CaseDetails getCaseById(Long id);



    @Modifying
    @Transactional
    @Query(value = "UPDATE case_details SET fact_file_code = :fCode WHERE id = :id", nativeQuery = true)
    int updateFactFileById(@Param("id") Long id, @Param("fCode") String fCode);

    @Modifying
    @Transactional
    @Query(value = "UPDATE case_details SET fact_feedback = :feedback WHERE id = :id", nativeQuery = true)
    int updateFeedback(@Param("id") Long id, @Param("feedback") String feedback);


    @Query(value="select id, assigned_to, case_deadline, case_description, case_priority, case_title ,case_type, client" +
            ", lawyer , case_status ,fact_file_code," +
            "fact_feedback, comp_flag from case_details c, case_documents d where c.fact_file_code=d.doc_id and d.doc_approval='A' AND NOT EXISTS ( SELECT 1 FROM  reply_details r WHERE r.case_id = c.id)",nativeQuery = true)
    List<CaseDetails> findApprovedCasesWithNonNullFactFileCode();


    @Query(value = "Select * from case_details where fact_file_code is not NULL ",
            nativeQuery = true)
    List<CaseDetails> findFactFiledCases();

    @Modifying
    @Transactional
    @Query(value = "UPDATE case_details SET case_status = 'R' WHERE id = :id", nativeQuery = true)
    int returnCase(@Param("id") Long id);

    @Query(value= "SELECT COALESCE(case_title, '') AS caseTitle, COALESCE(id, '') AS caseId, COALESCE(case_deadline, '') AS caseDeadline, COALESCE(lawyer, '') AS lawyer FROM  case_details  WHERE  LOWER(case_title) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<Object[]> findCaseDetailsByQuery(@Param("query") String query);


    @Modifying
    @Transactional
    @Query(value = "UPDATE case_details SET case_status = 'C' WHERE id = :id", nativeQuery = true)
    int closeCase(@Param("id") Long id);


}

