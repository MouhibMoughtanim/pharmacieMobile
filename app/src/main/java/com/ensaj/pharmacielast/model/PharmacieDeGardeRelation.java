package com.ensaj.pharmacielast.model;

import java.util.Date;

public class PharmacieDeGardeRelation {
    private int pharmaciePK;
    private int gardePK;
    private Date dateDebut;

    public PharmacieDeGardeRelation() {
    }

    public int getPharmaciePK() {
        return pharmaciePK;
    }

    public void setPharmaciePK(int pharmaciePK) {
        this.pharmaciePK = pharmaciePK;
    }

    public int getGardePK() {
        return gardePK;
    }

    public void setGardePK(int gardePK) {
        this.gardePK = gardePK;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
}
