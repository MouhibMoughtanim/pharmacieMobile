package com.ensaj.pharmacielast.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.repository.PharmacieRepository;

import java.util.List;

public class PharmacieViewModel extends ViewModel {

    private PharmacieRepository pharmacieRepository;

    public PharmacieViewModel(){
        pharmacieRepository = PharmacieRepository.getInstance();
    }


    public void addPharmacie(Pharmacie pharmacie,int user_id){
        pharmacieRepository.addPharmacie(pharmacie,user_id);
    }

    public LiveData<Pharmacie> getPharmacieByUserId(){
        return pharmacieRepository.getPharmacieByUserId();
    }

    public LiveData<List<Pharmacie>> getPharmacieByZoneId(){
        return pharmacieRepository.getPharmacieByZoneId();
    }

    public LiveData<List<Pharmacie>> getPharmacieByGardeId(){
        return pharmacieRepository.getPharmacieByGardeId();
    }

    public void searchPharmacieByUserIdApi(int user_id){
        pharmacieRepository.searchPharmacieByUserIdApi(user_id);
    }

    public void searchPharmacieByZoneIdApi(int zone_id){
        pharmacieRepository.searchPharmacieByZoneIdApi(zone_id);
    }

    public void searchPharmacieByGardeIdApi(int garde_id){
        pharmacieRepository.searchPharmacieByGardeIdApi(garde_id);
    }
}
