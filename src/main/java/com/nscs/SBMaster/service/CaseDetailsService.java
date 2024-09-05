package com.nscs.SBMaster.service;

import com.nscs.SBMaster.model.CaseDetails;
import com.nscs.SBMaster.repository.CaseDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CaseDetailsService {

    @Autowired
    private CaseDetailsRepository caseDetailsRepository;

    public CaseDetails createCase(CaseDetails caseDetails) {
        caseDetails.setCaseStatus("U");
        return caseDetailsRepository.save(caseDetails);
    }

    public List<CaseDetails> getAllCases() {
        return caseDetailsRepository.findAll();
    }

    public List<CaseDetails> getByStatus(String status){
        if(status.equals("C")){
            return caseDetailsRepository.findClosed(status);
        }
        return caseDetailsRepository.findByStatus(status);
    }

    public CaseDetails getCaseById(Long id){
        return caseDetailsRepository.getCaseById(id);
    }



    public void updateCase(CaseDetails caseDetails) {
        CaseDetails existingCase = caseDetailsRepository.findById(caseDetails.getId()).orElse(null);
        if (existingCase != null) {
            existingCase.setAssignedTo(caseDetails.getAssignedTo());
            existingCase.setCaseDeadline(caseDetails.getCaseDeadline());
            existingCase.setCasePriority(caseDetails.getCasePriority());
            existingCase.setCaseStatus("A");
            caseDetailsRepository.save(existingCase);
        }
    }


    public List<CaseDetails> getApprovedCases() {
        return caseDetailsRepository.findApprovedCases();
    }


    public List<CaseDetails> getFactFiledCases() {
        return caseDetailsRepository.findFactFiledCases();
    }



    public void updateFactFile(Long id, String fCode) {
        int rowsAffected = caseDetailsRepository.updateFactFileById(id, fCode);
        if (rowsAffected == 0) {
            throw new RuntimeException("Update failed, no rows affected.");
        }
    }

    public void updateCaseFeedback(Long id, String feedback) {
        int rowsAffected = caseDetailsRepository.updateFeedback(id, feedback);
        if (rowsAffected == 0) {
            throw new RuntimeException("Update failed, no rows affected.");
        }
    }
    public List<CaseDetails> getApprovedCasesWithNonNullFactFileCode() {
        return caseDetailsRepository.findApprovedCasesWithNonNullFactFileCode();
    }



    public void returnCase(Long id) {
        int rowsAffected=caseDetailsRepository.returnCase(id);
        if(rowsAffected==0){
            throw new RuntimeException("Update failed, no rows affected.");
        }
    }

    public List<Map<String, Object>> searchCaseDetails(String query) {
        List<Object[]> results = caseDetailsRepository.findCaseDetailsByQuery(query);
        List<Map<String, Object>> caseDetailsList = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> caseDetailMap = new HashMap<>();
            caseDetailMap.put("caseTitle", result[0]);
            caseDetailMap.put("caseId", result[1]);
            caseDetailMap.put("caseDeadline", result[2]);
            caseDetailMap.put("lawyer", result[3]);
            caseDetailsList.add(caseDetailMap);
        }

        return caseDetailsList;
    }



    public void deleteCaseById(Long caseId) {
        caseDetailsRepository.deleteById(caseId);
    }


}
