package com.example.LAB4_FP_GRUPO1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
public class Regions {

    @Id
    @Column(name = "region_id")
    private int id;
    @Column(name = "region_name")
    private String regioname;

    public int getid() {
        return id;
    }

    public void setRegionid(int id) {
        this.id = id;
    }

    public String getRegioname() {
        return regioname;
    }

    public void setRegioname(String regioname) {
        this.regioname = regioname;
    }
}
