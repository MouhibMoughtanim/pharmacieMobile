package com.ensaj.pharmacielast.model;

import java.io.Serializable;
import java.util.List;

public class Garde implements Serializable {
    private int idGarde;

    private String type;

    private List<PharmacieDeGarde> gardes ;

    public Garde() {
    }

    public int getIdGarde() {
        return idGarde;
    }

    public void setIdGarde(int idGarde) {
        this.idGarde = idGarde;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PharmacieDeGarde> getGardes() {
        return gardes;
    }

    public void setGardes(List<PharmacieDeGarde> gardes) {
        this.gardes = gardes;
    }
}
