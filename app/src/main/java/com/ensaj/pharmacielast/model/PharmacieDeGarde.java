package com.ensaj.pharmacielast.model;

import java.io.Serializable;
import java.sql.Date;

public class PharmacieDeGarde implements Serializable {

    private String dateFin;
    private String dateDebut;
    private Pharmacie pharmacie;
    private Garde garde;

    private  PharmacieDeGardeRelation pharmacieDeGardeRelation;

    public PharmacieDeGarde() {
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Pharmacie getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    public Garde getGarde() {
        return garde;
    }

    public void setGarde(Garde garde) {
        this.garde = garde;
    }

    public PharmacieDeGardeRelation getPharmacieDeGardeRelation() {
        return pharmacieDeGardeRelation;
    }

    public void setPharmacieDeGardeRelation(PharmacieDeGardeRelation pharmacieDeGardeRelation) {
        this.pharmacieDeGardeRelation = pharmacieDeGardeRelation;
    }
}
