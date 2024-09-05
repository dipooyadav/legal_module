package com.nscs.SBMaster.Entity;

import jakarta.persistence.*;

@Entity
@Table (name="epm_bid_stages" )
public class BidStage {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /*
    public String execute()
    {
        BidStageDao.saveBidStage(this);
        return "success";
    }*/
}
