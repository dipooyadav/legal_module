package com.nscs.SBMaster.Entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.io.File;
@Entity
@Table(name="epm_credentials")
public class Credentials {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String since="";
    private String upto="";
    private String cname="";
    private String ctype="";
    private String tech_staff="";
    private String nontech_staff="";
    private String credentialTerm="";
    private String domain="";
    private ArrayList<String> documentName;

    @Column(length=25600)
    private ArrayList<File> documentFile;


    @Column(length=25600)
    private ArrayList<byte[]> documentData;



    public ArrayList<File> getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(ArrayList<File> documentFile) {
        this.documentFile = documentFile;
    }
    public ArrayList<byte[]> getDocumentData() {
        return documentData;
    }
    public void setDocumentData(ArrayList<byte[]> documentData) {
        this.documentData = documentData;
    }
    public ArrayList<String> getDocumentName() {
        return documentName;
    }
    public void setDocumentName(ArrayList<String> documentName) {
        this.documentName = documentName;
    }

    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public String getCredentialTerm() {
        return credentialTerm;
    }
    public void setCredentialTerm(String credentialTerm) {
        this.credentialTerm = credentialTerm;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSince() {
        return since;
    }
    public void setSince(String since) {
        this.since = since;
    }
    public String getUpto() {
        return upto;
    }
    public void setUpto(String upto) {
        this.upto = upto;
    }
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getCtype() {
        return ctype;
    }
    public void setCtype(String ctype) {
        this.ctype = ctype;
    }
    public String getTech_staff() {
        return tech_staff;
    }
    public void setTech_staff(String tech_staff) {
        this.tech_staff = tech_staff;
    }
    public String getNontech_staff() {
        return nontech_staff;
    }
    public void setNontech_staff(String nontech_staff) {
        this.nontech_staff = nontech_staff;
    }

}
