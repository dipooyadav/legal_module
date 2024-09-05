package com.nscs.SBMaster.Entity;

import jakarta.persistence.*;

@Entity
public class CaseDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;

    private Long caseId;
    private String caseDocName;
    private String caseDocPath;



    private String docType;

    private String doc_approval;

    // Getters and setters
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseDocName() {
        return caseDocName;
    }

    public void setCaseDocName(String caseDocName) {
        this.caseDocName = caseDocName;
    }

    public String getCaseDocPath() {
        return caseDocPath;
    }

    public void setCaseDocPath(String caseDocPath) {
        this.caseDocPath = caseDocPath;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDoc_approval() {
        return doc_approval;
    }

    public void setDoc_approval(String doc_approval) {
        this.doc_approval = doc_approval;
    }
}
