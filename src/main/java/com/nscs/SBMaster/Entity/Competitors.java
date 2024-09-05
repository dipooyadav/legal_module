package com.nscs.SBMaster.Entity;


import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name="epm_competitors")
public class Competitors {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

}
