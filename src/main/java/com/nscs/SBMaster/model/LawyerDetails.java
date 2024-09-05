package com.nscs.SBMaster.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="lawyer_details")
public class LawyerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private Integer age;
    private String email;
    private String mobile;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getLawyerType() {
        return lawyerType;
    }

    public void setLawyerType(String lawyerType) {
        this.lawyerType = lawyerType;
    }

    public BigDecimal getTimeBillRate() {
        return timeBillRate;
    }

    public void setTimeBillRate(BigDecimal timeBillRate) {
        this.timeBillRate = timeBillRate;
    }

    public BigDecimal getCaseBillRate() {
        return caseBillRate;
    }

    public void setCaseBillRate(BigDecimal caseBillRate) {
        this.caseBillRate = caseBillRate;
    }

    public BigDecimal getMonthlyBillRate() {
        return monthlyBillRate;
    }

    public void setMonthlyBillRate(BigDecimal monthlyBillRate) {
        this.monthlyBillRate = monthlyBillRate;
    }

    @Lob
    private byte[] photo;

    private String lawyerType;
    private BigDecimal timeBillRate;
    private BigDecimal caseBillRate;
    private BigDecimal monthlyBillRate;

    // Getters and Setters
}
