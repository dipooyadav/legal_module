package com.nscspl.nkss.repository;

import com.nscspl.nkss.entity.MenuAccess;
import com.nscspl.nkss.model.Blank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;

@EnableJpaRepositories
public interface MenuAccessRepository  extends JpaRepository<MenuAccess,Long> {



//    @Query(value = "WITH MenuCTE AS (\n" +
//            "\n" +
//            "  SELECT id, m_action,m_icon,m_name,m_url, parent_id, 0 AS Level\n" +
//            "  FROM menu_access\n" +
//            "  WHERE parent_id IS NULL\n" +
//            "\n" +
//            "  UNION ALL\n" +
//            "\n" +
//            "  SELECT m.id, m.m_action,m.m_icon,m.m_name,m.m_url, m.parent_id, Level + 1\n" +
//            "  FROM menu_access m\n" +
//            "  INNER JOIN MenuCTE c ON m.parent_id = c.id\n" +
//            " )\n" +
//            "SELECT id, m_action,m_icon,m_name,m_url, parent_id, Level\n" +
//            "FROM MenuCTE\n" +
//            "ORDER BY Level, m_name",nativeQuery = true)
//     List<MenuAccess> getMenu();


     @Query(value = "WITH MenuCTE AS (\n" +
             "\n" +
             "  SELECT id, m_action,m_icon,m_name,m_url, parent_id, 0 AS Level\n" +
             "  FROM menu_access\n" +
             "\n" +
             "  UNION ALL\n" +
             "\n" +
             "  SELECT m.id, m.m_action,m.m_icon,m.m_name,m.m_url, m.parent_id, Level + 1\n" +
             "  FROM menu_access m\n" +
             "  INNER JOIN MenuCTE c ON m.parent_id = c.id\n" +
             " )\n" +
             "SELECT id, m_action,m_icon,m_name,m_url, parent_id, Level\n" +
             "FROM MenuCTE\n" +
             "ORDER BY Level",nativeQuery = true)
    List<MenuAccess> getMenu();

     @Query(value = "SELECT * FROM menu_access WHERE parent_id IS NULL ORDER BY m_name ASC",nativeQuery = true)
     List<MenuAccess>getNullParentList();

     @Query(value = "SELECT * FROM menu_access WHERE parent_id =:parentId",nativeQuery = true)
     List<MenuAccess>getLevelById(Long parentId);


     @Query(value = "\n" +
             "WITH MenuCTE AS (\n" +
             "    SELECT id, m_name, parent_id, 0 AS level, CAST(m_name AS NVARCHAR(MAX)) AS menuPath\n" +
             "    FROM menu_access\n" +
             "    WHERE parent_id IS NULL -- Assuming NULL parentId indicates the root level\n" +
             "    UNION ALL\n" +
             "    SELECT m.id, m.m_name, m.parent_id, cte.level + 1, CAST(CONCAT(cte.menuPath, ' > ', m.m_name) AS NVARCHAR(MAX))\n" +
             "    FROM menu_access AS m\n" +
             "    INNER JOIN MenuCTE AS cte ON m.parent_id = cte.id\n" +
             ")\n" +
             "SELECT id, m_name, parent_id, level, menuPath\n" +
             "FROM MenuCTE\n" +
             "ORDER BY id,level;",nativeQuery = true)
     List<Blank>findAllMenuByIdAndLevel();

            @Query(value = "SELECT DISTINCT s_mnu.mnu_ename, s_mnu.mnu_hname, s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno, s_mnu.mnu_next, " +
                    "COALESCE(s_mnu.mnu_pageDisplayType, '') as mnu_pageDisplayType, mnu_moduleno, mnu_sno, mnu_wordno, mnuust_flag,s_mnu.mnu_path " +
                    "FROM modules, s_mnu " +
                    "LEFT OUTER JOIN s_mnuust ON s_mnu.mnu_sno = s_mnuust.mnuust_mnusno AND s_mnuust.mnuust_userid = :ustid " +
                    "WHERE s_mnu.mnu_lvlno = '  ' " +
                    "AND s_mnu.mnu_lvlsno <> '99' " +
                    "AND sno = mnu_moduleno " +
                    "AND active = 'Y' " +
                    "AND mnu_application IN ('C', 'A') " +
                    "ORDER BY s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno", nativeQuery = true)
        String[][] getAccessRightMenuUst(String ustid);

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
            "ORDER BY s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno", nativeQuery = true)
    String[][] findDistinctByLvlnoAndActiveAndMnuApplication(@Param("lvlno") String lvlno, @Param("rolekid") String rolekid);

    @Query(value = "SELECT DISTINCT s_mnu.mnu_ename, s_mnu.mnu_hname, s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno, s_mnu.mnu_next, " +
            "COALESCE(s_mnu.mnu_pageDisplayType, '') as mnu_pageDisplayType, mnu_moduleno, mnu_sno, mnu_wordno, mnuust_flag,s_mnu.mnu_path " +
            "FROM modules, s_mnu " +
            "LEFT OUTER JOIN s_mnuust ON s_mnu.mnu_sno = s_mnuust.mnuust_mnusno AND s_mnuust.mnuust_userid = :ustid " +
            "WHERE s_mnu.mnu_lvlno = '  ' " +
            "AND s_mnu.mnu_lvlsno <> '99' " +
            "AND sno = mnu_moduleno " +
            "AND active = 'Y' " +
            "AND mnu_application IN ('C', 'A') " +
            "ORDER BY s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno", nativeQuery = true)
    String[][] getAccessRightMenuUstAR(String ustid);

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
            "ORDER BY s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno", nativeQuery = true)
    String[][] findDistinctByLvlnoAndActiveAndMnuApplicationAR(@Param("lvlno") String lvlno, @Param("rolekid") String rolekid);

    @Query(value = "SELECT DISTINCT s_mnu.mnu_ename, s_mnu.mnu_hname, s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno, s_mnu.mnu_next, " +
            "COALESCE(s_mnu.mnu_pageDisplayType, '') as mnu_pageDisplayType, mnu_moduleno, mnu_sno, mnu_wordno, mnuust_flag,s_mnu.mnu_path " +
            "FROM modules, s_mnu " +
            "LEFT OUTER JOIN s_mnuust ON s_mnu.mnu_sno = s_mnuust.mnuust_mnusno AND s_mnuust.mnuust_userid = :ustid " +
            "WHERE s_mnu.mnu_lvlno = '' " +
            "AND s_mnu.mnu_lvlsno <> '99' " +
            "AND sno = mnu_moduleno " +
            "AND active = 'Y' " +
            "AND mnu_application IN ('C', 'A') " +
            "and s_mnuust.mnuust_flag='Y'"+
            "ORDER BY s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno", nativeQuery = true)
    String[][] getAccessRightMenuUstLogin(String ustid);

    @Query(value = "SELECT DISTINCT s_mnu.mnu_ename, s_mnu.mnu_h name, s_mnu.mnu_lvlno, s_mnu.mnu_lvlsno, " +
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

    @Modifying
    @Transactional
    @Query(value="UPDATE s_mnuust SET mnuust_flag = 'Y' WHERE mnuust_userid = :userid AND mnuust_mnusno = :mnusno", nativeQuery = true)
    void updateAccessRightTrue(@Param("userid") String userid, @Param("mnusno") String mnusno);

    @Modifying
    @Transactional
    @Query(value="UPDATE s_mnuust SET mnuust_flag = 'N' WHERE mnuust_userid = :userid AND mnuust_mnusno = :mnusno", nativeQuery = true)
    void updateAccessRightFalse(@Param("userid") String userid, @Param("mnusno") String mnusno);

    @Query(value = "select  ust_kid,ust_ename,ust_type  from  s_ust where  ust_status = 'Y'", nativeQuery = true)
    String[][] designation();











}
