package com.nscs.SBMaster.model;

import jakarta.persistence.*;

@Entity
public class ReplyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long caseId;
    private String factFileCode;
    private String caseDocName;
    private String address;
    private String date;
    private String trackingID;
    private String replyMedium;
    private String emailId;


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getReplyMedium() {
        return replyMedium;
    }

    public void setReplyMedium(String replyMedium) {
        this.replyMedium = replyMedium;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactFileCode() {
        return factFileCode;
    }

    public void setFactFileCode(String factFileCode) {
        this.factFileCode = factFileCode;
    }

    public String getCaseDocName() {
        return caseDocName;
    }

    public void setCaseDocName(String caseDocName) {
        this.caseDocName = caseDocName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
    }
}
