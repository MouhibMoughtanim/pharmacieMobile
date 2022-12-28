package com.ensaj.pharmacielast.repository;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.request.PharmacieApiClient;

public class PharmacieRepository {
    private static PharmacieRepository instance;
    private PharmacieApiClient pharmacieApiClient;

    public static PharmacieRepository getInstance() {
        if (instance == null) {
            instance = new PharmacieRepository();

        }
        return instance;
    }

    public PharmacieRepository(){
        pharmacieApiClient = PharmacieApiClient.getInstance();
    }


    public void addPharmacie(Pharmacie pharmacie){
        pharmacieApiClient.addPharmacieApi(pharmacie);
    }

}
