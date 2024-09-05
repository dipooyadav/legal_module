package com.nscs.SBMaster.Entity;


import jakarta.persistence.*;

@Table(name="s_usr")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "usr_kid")
    private int id;

    @Column(name = "usr_ename")
    private String name;

    @Column(name = "usr_mobileno")
    private String mobailNo;

    @Column(name = "usr_pswd")
    private String password;

    @Column(name="role")
    private String role;

    @Column(name = "usr_hname")
    private String usrHname;


    @Column(name = "usr_ustid")
    private int usrUstid;

    @Column(name = "usr_psdate")
    private String usrPsdate;

    @Column(name = "usr_psnew")
    private String usrPsnew;

    @Column(name = "usr_odate")
    private String usrOdate;

    @Column(name = "usr_cdate")
    private String usrCdate;

    @Column(name = "usr_ataid")
    private String usrAtaid;

    @Column(name = "usr_flag")
    private String usrFlag;

    @Column(name = "usr_allacc")
    private String usrAllacc;

    @Column(name = "usr_branchid")
    private String usrBranchid;

    @Column(name = "usr_bankid")
    private String usrBankid;

    @Column(name = "checksum")
    private String checksum;

    @Column(name = "usr_auth")
    private String usrAuth;

    @Column(name = "usr_ecno")
    private String usrEcno;

    @Column(name = "usr_delac")
    private String usrDelac;

    @Column(name = "usr_createid")
    private String usrCreateid;

    @Column(name = "usr_entdate")
    private String usrEntdate;

    @Column(name = "usr_hinfirst")
    private String usrHinfirst;

    @Column(name = "usr_valuedt")
    private String usrValuedt;

    @Column(name = "usr_doj")
    private String usrDoj;

    @Column(name = "usr_retail")
    private String usrRetail;

    @Column(name = "usr_type")
    private String usrType;

    @Column(name = "usr_exprdate")
    private String usrExprdate;

    @Column(name = "usr_brncode")
    private String usrBrncode;

    @Column(name = "usr_ustDbalevel")
    private String usrUstDbalevel;

    @Column(name = "usr_auforIntrbrnTr")
    private String usrAuforIntrbrnTr;

    @Column(name = "usr_usttype")
    private String usrUsttype;

    @Column(name = "usr_isMultiBrnUser")
    private String usrIsMultiBrnUser;

    @Column(name = "usr_valuedtAuth")
    private String usrValuedtAuth;

    @Column(name = "usr_pswdlifetimevalid")
    private String usrPswdlifetimevalid;

    @Column(name = "usr_dualAuth")
    private String usrDualAuth;

    @Column(name = "usr_cashTranYN")
    private String usrCashTranYN;

    @Column(name = "usr_mngrCustId")
    private Integer usrMngrCustId;

    @Column(name = "COMP_KID")
    private String compKid;

    @Column(name = "usr_deptid")
    private String usrDeptid;

    @Column(name = "usr_code")
    private String userCode;

    @Column(name="usr_emailid")
    private String email;

    public String getEmailid() {
        return email;
    }

    public void setEmailid(String email) {
        this.email = email;
    }

    public String getUsrHname() {
        return usrHname;
    }

    public void setUsrHname(String usrHname) {
        this.usrHname = usrHname;
    }

    public int getUsrUstid() {
        return usrUstid;
    }

    public void setUsrUstid(int usrUstid) {
        this.usrUstid = usrUstid;
    }

    public String getUsrPsdate() {
        return usrPsdate;
    }

    public void setUsrPsdate(String usrPsdate) {
        this.usrPsdate = usrPsdate;
    }

    public String getUsrPsnew() {
        return usrPsnew;
    }

    public void setUsrPsnew(String usrPsnew) {
        this.usrPsnew = usrPsnew;
    }

    public String getUsrOdate() {
        return usrOdate;
    }

    public void setUsrOdate(String usrOdate) {
        this.usrOdate = usrOdate;
    }

    public String getUsrCdate() {
        return usrCdate;
    }

    public void setUsrCdate(String usrCdate) {
        this.usrCdate = usrCdate;
    }

    public String getUsrAtaid() {
        return usrAtaid;
    }

    public void setUsrAtaid(String usrAtaid) {
        this.usrAtaid = usrAtaid;
    }

    public String getUsrFlag() {
        return usrFlag;
    }

    public void setUsrFlag(String usrFlag) {
        this.usrFlag = usrFlag;
    }

    public String getUsrAllacc() {
        return usrAllacc;
    }

    public void setUsrAllacc(String usrAllacc) {
        this.usrAllacc = usrAllacc;
    }

    public String getUsrBranchid() {
        return usrBranchid;
    }

    public void setUsrBranchid(String usrBranchid) {
        this.usrBranchid = usrBranchid;
    }

    public String getUsrBankid() {
        return usrBankid;
    }

    public void setUsrBankid(String usrBankid) {
        this.usrBankid = usrBankid;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getUsrAuth() {
        return usrAuth;
    }

    public void setUsrAuth(String usrAuth) {
        this.usrAuth = usrAuth;
    }

    public String getUsrEcno() {
        return usrEcno;
    }

    public void setUsrEcno(String usrEcno) {
        this.usrEcno = usrEcno;
    }

    public String getUsrDelac() {
        return usrDelac;
    }

    public void setUsrDelac(String usrDelac) {
        this.usrDelac = usrDelac;
    }

    public String getUsrCreateid() {
        return usrCreateid;
    }

    public void setUsrCreateid(String usrCreateid) {
        this.usrCreateid = usrCreateid;
    }

    public String getUsrEntdate() {
        return usrEntdate;
    }

    public void setUsrEntdate(String usrEntdate) {
        this.usrEntdate = usrEntdate;
    }

    public String getUsrHinfirst() {
        return usrHinfirst;
    }

    public void setUsrHinfirst(String usrHinfirst) {
        this.usrHinfirst = usrHinfirst;
    }

    public String getUsrValuedt() {
        return usrValuedt;
    }

    public void setUsrValuedt(String usrValuedt) {
        this.usrValuedt = usrValuedt;
    }

    public String getUsrDoj() {
        return usrDoj;
    }

    public void setUsrDoj(String usrDoj) {
        this.usrDoj = usrDoj;
    }

    public String getUsrRetail() {
        return usrRetail;
    }

    public void setUsrRetail(String usrRetail) {
        this.usrRetail = usrRetail;
    }

    public String getUsrType() {
        return usrType;
    }

    public void setUsrType(String usrType) {
        this.usrType = usrType;
    }

    public String getUsrExprdate() {
        return usrExprdate;
    }

    public void setUsrExprdate(String usrExprdate) {
        this.usrExprdate = usrExprdate;
    }

    public String getUsrBrncode() {
        return usrBrncode;
    }

    public void setUsrBrncode(String usrBrncode) {
        this.usrBrncode = usrBrncode;
    }

    public String getUsrUstDbalevel() {
        return usrUstDbalevel;
    }

    public void setUsrUstDbalevel(String usrUstDbalevel) {
        this.usrUstDbalevel = usrUstDbalevel;
    }

    public String getUsrAuforIntrbrnTr() {
        return usrAuforIntrbrnTr;
    }

    public void setUsrAuforIntrbrnTr(String usrAuforIntrbrnTr) {
        this.usrAuforIntrbrnTr = usrAuforIntrbrnTr;
    }

    public String getUsrUsttype() {
        return usrUsttype;
    }

    public void setUsrUsttype(String usrUsttype) {
        this.usrUsttype = usrUsttype;
    }

    public String getUsrIsMultiBrnUser() {
        return usrIsMultiBrnUser;
    }

    public void setUsrIsMultiBrnUser(String usrIsMultiBrnUser) {
        this.usrIsMultiBrnUser = usrIsMultiBrnUser;
    }

    public String getUsrValuedtAuth() {
        return usrValuedtAuth;
    }

    public void setUsrValuedtAuth(String usrValuedtAuth) {
        this.usrValuedtAuth = usrValuedtAuth;
    }

    public String getUsrPswdlifetimevalid() {
        return usrPswdlifetimevalid;
    }

    public void setUsrPswdlifetimevalid(String usrPswdlifetimevalid) {
        this.usrPswdlifetimevalid = usrPswdlifetimevalid;
    }

    public String getUsrDualAuth() {
        return usrDualAuth;
    }

    public void setUsrDualAuth(String usrDualAuth) {
        this.usrDualAuth = usrDualAuth;
    }

    public String getUsrCashTranYN() {
        return usrCashTranYN;
    }

    public void setUsrCashTranYN(String usrCashTranYN) {
        this.usrCashTranYN = usrCashTranYN;
    }





    public Integer getUsrMngrCustId() {
        return usrMngrCustId;
    }

    public void setUsrMngrCustId(Integer usrMngrCustId) {
        this.usrMngrCustId = usrMngrCustId;
    }

    public String getCompKid() {
        return compKid;
    }

    public void setCompKid(String compKid) {
        this.compKid = compKid;
    }

    public String getUsrDeptid() {
        return usrDeptid;
    }

    public void setUsrDeptid(String usrDeptid) {
        this.usrDeptid = usrDeptid;
    }



    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}


    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getMobailNo() {return mobailNo;}
    public void setMobailNo(String mobailNo) {this.mobailNo = mobailNo;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobailNo='" + mobailNo + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", usrHname='" + usrHname + '\'' +
                ", usrUstid=" + usrUstid +
                ", usrPsdate='" + usrPsdate + '\'' +
                ", usrPsnew='" + usrPsnew + '\'' +
                ", usrOdate='" + usrOdate + '\'' +
                ", usrCdate='" + usrCdate + '\'' +
                ", usrAtaid='" + usrAtaid + '\'' +
                ", usrFlag='" + usrFlag + '\'' +
                ", usrAllacc='" + usrAllacc + '\'' +
                ", usrBranchid='" + usrBranchid + '\'' +
                ", usrBankid='" + usrBankid + '\'' +
                ", checksum='" + checksum + '\'' +
                ", usrAuth='" + usrAuth + '\'' +
                ", usrEcno='" + usrEcno + '\'' +
                ", usrDelac='" + usrDelac + '\'' +
                ", usrCreateid='" + usrCreateid + '\'' +
                ", usrEntdate='" + usrEntdate + '\'' +
                ", usrHinfirst='" + usrHinfirst + '\'' +
                ", usrValuedt='" + usrValuedt + '\'' +
                ", usrDoj='" + usrDoj + '\'' +
                ", usrRetail='" + usrRetail + '\'' +
                ", usrType='" + usrType + '\'' +
                ", usrExprdate='" + usrExprdate + '\'' +
                ", usrBrncode='" + usrBrncode + '\'' +
                ", usrUstDbalevel='" + usrUstDbalevel + '\'' +
                ", usrAuforIntrbrnTr='" + usrAuforIntrbrnTr + '\'' +
                ", usrUsttype='" + usrUsttype + '\'' +
                ", usrIsMultiBrnUser='" + usrIsMultiBrnUser + '\'' +
                ", usrValuedtAuth='" + usrValuedtAuth + '\'' +
                ", usrPswdlifetimevalid='" + usrPswdlifetimevalid + '\'' +
                ", usrDualAuth='" + usrDualAuth + '\'' +
                ", usrCashTranYN='" + usrCashTranYN + '\'' +
                ", usrMngrCustId=" + usrMngrCustId +
                ", compKid='" + compKid + '\'' +
                ", usrDeptid='" + usrDeptid + '\'' +
                ", userCode='" + userCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
