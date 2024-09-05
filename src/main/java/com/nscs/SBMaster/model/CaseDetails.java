package com.nscs.SBMaster.model;

import jakarta.persistence.*;


@Entity
public class CaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caseTitle;
    private String lawyer;
    private int caseType;
    private String client;
    private String caseDescription;

    @Transient
    private String caseTypeEname;



    @Transient
    private String assignedToEname;


    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "case_deadline")
    private String caseDeadline;

    @Column(name = "case_priority")
    private String casePriority;



    @Column(name = "case_status")
    private String caseStatus;


    @Column(name = "fact_file_code")
    private String factFileCode;

    @Column(name = "fact_Feedback")
    private String factFeedback;

    @Transient
    private String factFileStatus;


    @Column(name = "comp_flag" , length = 1)
    private String compFlag;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public int getCaseType() {
        return caseType;
    }

    public void setCaseType(int caseType) {
        this.caseType = caseType;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public String getCaseTypeEname() {
        return caseTypeEname;
    }

    public void setCaseTypeEname(String caseTypeEname) {
        this.caseTypeEname = caseTypeEname;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getCaseDeadline() {
        return caseDeadline;
    }

    public void setCaseDeadline(String caseDeadline) {
        this.caseDeadline = caseDeadline;
    }

    public String getCasePriority() {
        return casePriority;
    }

    public void setCasePriority(String casePriority) {
        this.casePriority = casePriority;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }


    public String getAssignedToEname() {
        return assignedToEname;
    }

    public void setAssignedToEname(String assignedToEname) {
        this.assignedToEname = assignedToEname;
    }



    public String getFactFileCode() {
        return factFileCode;
    }

    public void setFactFileCode(String factFileCode) {
        this.factFileCode = factFileCode;
    }


    public String getFactFeedback() {
        return factFeedback;
    }

    public void setFactFeedback(String factFeedback) {
        this.factFeedback = factFeedback;
    }


    public String getFactFileStatus() {
        return factFileStatus;
    }

    public void setFactFileStatus(String factFileStatus) {
        this.factFileStatus = factFileStatus;
    }

    public String getCompFlag() {
        return compFlag;
    }

    public void setCompFlag(String compFlag) {
        this.compFlag = compFlag;
    }

}

