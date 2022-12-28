package com.ensaj.pharmacielast.viewModels;

import androidx.lifecycle.ViewModel;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.repository.PharmacieRepository;

public class PharmacieViewModel extends ViewModel {

    private PharmacieRepository pharmacieRepository;
    public PharmacieViewModel(){
        pharmacieRepository = PharmacieRepository.getInstance();
    }


    public void addPharmacie(Pharmacie pharmacie){
        pharmacieRepository.addPharmacie(pharmacie);
    }

}
