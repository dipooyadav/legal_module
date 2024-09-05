package com.nscs.SBMaster.repository;

import com.nscs.SBMaster.Entity.CaseDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CaseDocumentsRepository extends JpaRepository<CaseDocuments, Long> {


    @Modifying
    @Transactional
    @Query(value = "UPDATE case_documents SET doc_approval = 'R' WHERE doc_id = :id", nativeQuery = true)
    int returnFactFile(@Param("id") Long id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE case_documents SET doc_approval = 'A' WHERE doc_id = :id", nativeQuery = true)
    int approveFactFile(@Param("id") Long id);



    @Query(value = "SELECT * from case_documents WHERE doc_id = :id", nativeQuery = true)
    CaseDocuments findByFactCode(Long id);
}
