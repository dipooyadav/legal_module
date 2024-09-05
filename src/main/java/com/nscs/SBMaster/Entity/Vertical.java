package com.nscs.SBMaster.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "epm_verticals")
public class Vertical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String vertical_name;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getVertical_name() {
        return vertical_name;
    }
    public void setVertical_name(String vertical_name) {
        this.vertical_name = vertical_name;
    }


}
