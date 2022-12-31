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

    public void searchPharmacieByUserIdApi(int user_id){
        pharmacieRepository.searchPharmacieByUserIdApi(user_id);
    }
}
