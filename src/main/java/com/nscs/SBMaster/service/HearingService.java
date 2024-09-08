package com.nscs.SBMaster.service;

import com.nscs.SBMaster.Entity.CaseDocuments;
import com.nscs.SBMaster.Entity.HearingDetails;
import com.nscs.SBMaster.repository.CaseDetailsRepository;
import com.nscs.SBMaster.repository.CaseDocumentsRepository;
import com.nscs.SBMaster.repository.HearingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class HearingService {
    @Autowired
    private HearingRepository hearingRepository;

    @Autowired
    private CaseDocumentsRepository caseDocumentsRepository;

    @Autowired
    private CaseDetailsRepository caseDetailsRepository;

    @Value("${closeCase.upload-dir}")
    private String caseClosedUpload;



    public HearingDetails saveHearingDetails(HearingDetails hearingDetails) {
        return hearingRepository.save(hearingDetails);
    }

    public List<HearingDetails> getAllUpcomingHearings() {
        return hearingRepository.findUpcomingHearings();
    }

    public List<HearingDetails> getHearingHistory() {
        return hearingRepository.findHearingHistory();
    }

    public HearingDetails updateHearingDetails(HearingDetails hearingDetails, MultipartFile[] attachedDocs) {
        HearingDetails oldHearingDetail = hearingRepository.findById(hearingDetails.getHearingId()).orElse(null);
        if(oldHearingDetail!=null){
            oldHearingDetail.setCaseHearingResult(hearingDetails.getCaseHearingResult());
            oldHearingDetail.setCasePostDate(hearingDetails.getCasePostDate());
            oldHearingDetail.setCaseHeardJudges(hearingDetails.getCaseHeardJudges());
            oldHearingDetail.setCaseHrJudgement(hearingDetails.getCaseHrJudgement());
            if(hearingDetails.getCaseHearingResult().equals("C")){
                Long caseId = oldHearingDetail.getCaseId();
                String caseDir = caseClosedUpload + caseId;

                // Create directory for the caseId if it doesn't exist
                File caseDirFile = new File(caseDir);
                if (!caseDirFile.exists()) {
                    caseDirFile.mkdirs();
                }
                for (MultipartFile file : attachedDocs) {
                    if (!file.isEmpty()) {

                        String filePath = caseDir + "/" + file.getOriginalFilename();
                        try {
                            file.transferTo(new File(filePath));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        // Save file information in the database
                        CaseDocuments caseDocument = new CaseDocuments();
                        caseDocument.setCaseId(caseId);
                        caseDocument.setCaseDocName(file.getOriginalFilename());
                        caseDocument.setCaseDocPath(filePath);
                        caseDocument.setDocType("closed");
                        caseDocumentsRepository.save(caseDocument);
                        if (caseDocument.getDocId() != null)
                            oldHearingDetail.setResultDocId(caseDocument.getDocId());
                    }
                }
            }
            caseDetailsRepository.closeCase(oldHearingDetail.getCaseId());
            return hearingRepository.save(oldHearingDetail);

        }
        return null;
    }
}
