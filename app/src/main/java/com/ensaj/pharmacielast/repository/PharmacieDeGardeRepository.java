package com.ensaj.pharmacielast.repository;

import com.ensaj.pharmacielast.model.PharmacieDeGarde;
import com.ensaj.pharmacielast.request.PharmacieDeGardeApiClient;

public class PharmacieDeGardeRepository {
    private static PharmacieDeGardeRepository instance;
    private PharmacieDeGardeApiClient pharmacieDeGardeApiClient;

    public static PharmacieDeGardeRepository getInstance() {
        if (instance == null) {
            instance = new PharmacieDeGardeRepository();

        }
        return instance;
    }

    public PharmacieDeGardeRepository(){
        pharmacieDeGardeApiClient = PharmacieDeGardeApiClient.getInstance();
    }


    public void addPharmacieDeGarde(PharmacieDeGarde pharmacieDeGarde,String debut,String fin){
        pharmacieDeGardeApiClient.addPharmacieDeGardeApi(pharmacieDeGarde,debut,fin);
        System.out.println("REPOSITORY");

    }

}
