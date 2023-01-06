package com.ensaj.pharmacielast.repository;

import androidx.lifecycle.LiveData;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.request.PharmacieApiClient;

import java.util.List;

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
    public LiveData<Pharmacie> getPharmacieByUserId(){return pharmacieApiClient.getPharmacieByUserId();}
    public LiveData<List<Pharmacie>> getPharmacieByZoneId(){return pharmacieApiClient.getPharmaciesByZoneId();}
    public LiveData<List<Pharmacie>> getPharmacieByGardeId(){return pharmacieApiClient.getPharmaciesByGardeId();}



    public void addPharmacie(Pharmacie pharmacie,int user_id){
        pharmacieApiClient.addPharmacieApi(pharmacie,user_id);
    }
    public void searchPharmacieByUserIdApi(int user_id){
        pharmacieApiClient.getPharmacieByUserIdApi(user_id);
    }
    public void searchPharmacieByZoneIdApi(int zone_id){
        pharmacieApiClient.getPharmacieByZoneIdApi(zone_id);
    }
    public void searchPharmacieByGardeIdApi(int garde_id){
        pharmacieApiClient.getPharmacieByGardeIdApi(garde_id);
    }


}
