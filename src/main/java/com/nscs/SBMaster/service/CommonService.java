package com.nscs.SBMaster.service;

import com.nscs.SBMaster.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {


    @Autowired
    private CommonRepository commonRepository;


    public List<Map<String, Object>> searchAssupmstDetails(String query) {
        List<Object[]> results = commonRepository.findassupmstByQuery(query);
        List<Map<String, Object>> caseDetailsList = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> caseDetailMap = new HashMap<>();
            caseDetailMap.put("supplier", result[0]);
            caseDetailMap.put("supplierId", result[1]);

            caseDetailsList.add(caseDetailMap);
        }

        return caseDetailsList;
    }

}
