package com.nscs.SBMaster.Entity;

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
@Table(name="epm_remarks")
public class Remarks {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String remark;
    private String remarks;


}
