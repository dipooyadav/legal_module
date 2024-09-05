package com.nscs.SBMaster.repository;

import com.nscs.SBMaster.model.CaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CommonRepository extends JpaRepository<CaseDetails, Long> {

    @Query(value = "SELECT id, category_name FROM category_master", nativeQuery = true)
    List<Map<String, Object>> findAllCategories();

    @Query(value = "SELECT category_name FROM category_master where id=:id", nativeQuery = true)
    List<Map<String, Object>> getCategoryById(int id);

    @Query(value = "SELECT usr_kid, usr_ename FROM s_usr", nativeQuery = true)
    List<Map<String, Object>> getAllUsers();


    @Query(value = "SELECT usr_kid, usr_code, usr_ename, usr_ustid, usr_flag, usr_brncode, usr_emailid FROM s_usr where usr_kid=:id", nativeQuery = true)
    List<Map<String, Object>> getUserById(Long id);

    @Query(value= "SELECT concat(assupmst_code,' - ',assupmst_ename) as supplier , assupmst_kid AS kid  FROM  s_assupmst  WHERE  LOWER(assupmst_ename) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<Object[]> findassupmstByQuery(String query);
}

