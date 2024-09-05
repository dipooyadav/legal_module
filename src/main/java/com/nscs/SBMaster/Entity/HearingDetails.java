package com.nscs.SBMaster.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "case_hearing_details")
public class HearingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hearingId;

    private Long caseId;


    private String caseTitle;
    private String caseLawyer;
    private String caseHearingDate;
    private String caseHearingResult;
    private String casePostDate;
    private String caseHeardJudges;
    private String caseHrJudgement;

    @Column(name = "result_doc_id", nullable = true)
    private Long resultDocId;




    public Long getHearingId() {
        return hearingId;
    }

    public void setHearingId(Long hearingId) {
        this.hearingId = hearingId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseLawyer() {
        return caseLawyer;
    }

    public void setCaseLawyer(String caseLawyer) {
        this.caseLawyer = caseLawyer;
    }

    public String getHearingDate() {
        return caseHearingDate;
    }

    public void setHearingDate(String hearingDate) {
        this.caseHearingDate = hearingDate;
    }

    public String getCaseHearingResult() {
        return caseHearingResult;
    }

    public void setCaseHearingResult(String caseHearingResult) {
        this.caseHearingResult = caseHearingResult;
    }

    public String getCasePostDate() {
        return casePostDate;
    }

    public void setCasePostDate(String casePostDate) {
        this.casePostDate = casePostDate;
    }

    public String getCaseHeardJudges() {
        return caseHeardJudges;
    }

    public void setCaseHeardJudges(String caseHeardJudges) {
        this.caseHeardJudges = caseHeardJudges;
    }

    public String getCaseHrJudgement() {
        return caseHrJudgement;
    }

    public void setCaseHrJudgement(String caseHrJudgement) {
        this.caseHrJudgement = caseHrJudgement;
    }


    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaseHearingDate() {
        return caseHearingDate;
    }

    public void setCaseHearingDate(String caseHearingDate) {
        this.caseHearingDate = caseHearingDate;
    }

    public long getResultDocId() {
        return resultDocId;
    }

    public void setResultDocId(long resultDocId) {
        this.resultDocId = resultDocId;
    }

}

