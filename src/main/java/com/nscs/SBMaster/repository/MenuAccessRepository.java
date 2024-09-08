package com.nscs.SBMaster.repository;

import com.nscs.SBMaster.model.CaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuAccessRepository extends JpaRepository<CaseDetails,Long> {


    @Query(value = "SELECT DISTINCT s_mnu.mnu_ename, s_mnu.mnu_hname, s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno, s_mnu.mnu_next, " +
            "COALESCE(s_mnu.mnu_pageDisplayType, '') as mnu_pageDisplayType, mnu_moduleno, mnu_sno, mnu_wordno, mnuust_flag,s_mnu.mnu_path " +
            "FROM modules, s_mnu " +
            "LEFT OUTER JOIN s_mnuust ON s_mnu.mnu_sno = s_mnuust.mnuust_mnusno AND mnuust_userid = :ustid " +
            "WHERE s_mnu.mnu_lvlno = '' " +
            "AND s_mnu.mnu_lvlsno <> '99' " +
            "AND sno = mnu_moduleno " +
            "AND active = 'Y' " +
            "AND mnu_application IN ('C', 'A') " +
            "and s_mnuust.mnuust_flag='Y'"+
            "ORDER BY s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno", nativeQuery = true)
    String[][] getAccessRightMenuUstLogin(String ustid);


    @Query(value = "SELECT DISTINCT s_mnu.mnu_ename, s_mnu.mnu_hname, s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno, " +
            "s_mnu.mnu_next, COALESCE(s_mnu.mnu_pageDisplayType, '') as mnu_pageDisplayType, " +
            "mnu_moduleno, mnu_sno, mnu_wordno, mnuust_flag,s_mnu.mnu_path " +
            "FROM modules, s_mnu " +
            "LEFT OUTER JOIN s_mnuust ON s_mnu.mnu_sno = s_mnuust.mnuust_mnusno AND s_mnuust.mnuust_userid = :rolekid " +
            "WHERE s_mnu.mnu_lvlno = :lvlno " +
            "AND s_mnu.mnu_lvlsno <> '99' " +
            "AND sno = mnu_moduleno " +
            "AND active = 'Y' " +
            "AND mnu_application IN ('C', 'A') " +
            "and s_mnuust.mnuust_flag='Y'"+
            "ORDER BY s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno", nativeQuery = true)
    String[][] findDistinctByLvlnoAndActiveAndMnuApplicationLogin(@Param("lvlno") String lvlno, @Param("rolekid") String rolekid);




}
