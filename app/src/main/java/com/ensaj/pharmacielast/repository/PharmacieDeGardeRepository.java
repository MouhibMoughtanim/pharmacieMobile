package com.ensaj.pharmacielast.repository;

import androidx.lifecycle.LiveData;

import com.ensaj.pharmacielast.model.PharmacieDeGarde;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.request.PharmacieDeGardeApiClient;

import java.util.List;

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

    public LiveData<List<PharmacieDeGarde>> getAllPharmaciesDeGarde(){return pharmacieDeGardeApiClient.getPharmaciesDeGarde();}
    public LiveData<List<PharmacieDeGarde>> getAllPharmaciesDeGardeById(){return pharmacieDeGardeApiClient.getPharmaciesDeGardeById();}

    public void searchPharmaciesDeGarde(){
        pharmacieDeGardeApiClient.getPharmaciesDeGardeApi();
    }
     public void searchPharmaciesDeGardeById(int id){
        pharmacieDeGardeApiClient.getPharmaciesDeGardeByIdApi(id);
    }

}
