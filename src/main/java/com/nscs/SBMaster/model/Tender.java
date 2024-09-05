package com.nscs.SBMaster.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tender")

public class Tender {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id ;
        @Column(name="publish_date")
        private String publishDate ;
        @Column(name="due_date")
        private String dueDate ;
        @Column(name="name_of_work")
        private String nameOfWork ;
    @Column(name="emd_value")
    private String emdValue ;
    @Column(name="tender_fees")
    private String tenderFees ;
    @Column(name="mode_of_payment")
    private String modeofPayment ;
    @Column(name="tender_value")
    private String tenderValue ;
    @Column(name="msme_pref")
    private String msmePref ;
    @Column(name="zone")
    private String zone ;
    @Column(name="dept")
    private String dept ;
    @Column(name="division")
    private String division ;


    }

