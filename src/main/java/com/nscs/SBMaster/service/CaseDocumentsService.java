package com.nscs.SBMaster.service;

import com.nscs.SBMaster.Entity.CaseDocuments;
import com.nscs.SBMaster.model.ReplyDetails;
import com.nscs.SBMaster.repository.CaseDocumentsRepository;
import com.nscs.SBMaster.repository.ReplyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaseDocumentsService {



    @Autowired
    private CaseDocumentsRepository caseDocumentsRepository;

    @Autowired
    private ReplyDetailsRepository replyDetailsRepository;

    public String getFilePathByFileId(Long fileId) {
        Optional<CaseDocuments> caseDocumentOptional = caseDocumentsRepository.findById(fileId);
        if (caseDocumentOptional.isPresent()) {
            CaseDocuments caseDocument = caseDocumentOptional.get();
            return caseDocument.getCaseDocPath(); // Adjust this to your actual file path property
        }
        return null;
    }


    public void returnFactFile(Long id) {
        int rowsAffected=caseDocumentsRepository.returnFactFile(id);
        if(rowsAffected==0){
            throw new RuntimeException("Update failed, no rows affected.");
        }
    }


    public void approveFactFile(Long id) {
        int rowsAffected=caseDocumentsRepository.approveFactFile(id);
        if(rowsAffected==0){
            throw new RuntimeException("Update failed, no rows affected.");
        }
    }



    public CaseDocuments findByFactCode(String factCode) {
        return caseDocumentsRepository.findByFactCode(Long.valueOf(factCode));
    }




    public ReplyDetails saveReplyDetails(ReplyDetails replyDetails) {
        return replyDetailsRepository.save(replyDetails);
    }


    // Method to fetch a document by its ID
    public CaseDocuments getDocumentById(Long id) {
        Optional<CaseDocuments> document = caseDocumentsRepository.findById(id);
        if (document.isPresent()) {
            return document.get();
        } else {
            throw new RuntimeException("Document not found");
        }
    }
}
