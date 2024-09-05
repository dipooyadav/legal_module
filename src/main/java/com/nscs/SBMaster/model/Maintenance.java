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
@Table(name="maintenace_req")
public class Maintenance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id ;
        @Column(name="report_date")
        private String publishDate ;
        @Column(name="due_date")
        private String dueDate ;
        @Column(name="name_of_asset")
        private String nameOfAsset ;
        @Column(name="main_desc")
        private String Description ;
        @Column(name="reported_by")
        private String reportedBy ;


    }


