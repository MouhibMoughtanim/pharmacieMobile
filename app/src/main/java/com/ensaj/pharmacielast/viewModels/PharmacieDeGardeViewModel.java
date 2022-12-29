package com.ensaj.pharmacielast.viewModels;

import androidx.lifecycle.ViewModel;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.PharmacieDeGarde;
import com.ensaj.pharmacielast.repository.PharmacieDeGardeRepository;
import com.ensaj.pharmacielast.repository.PharmacieRepository;

public class PharmacieDeGardeViewModel extends ViewModel {
    private PharmacieDeGardeRepository pharmacieDeGardeRepository;
    public PharmacieDeGardeViewModel(){
        pharmacieDeGardeRepository = PharmacieDeGardeRepository.getInstance();
    }


    public void addPharmacieDeGarde(PharmacieDeGarde pharmacieDeGarde,String debut,String fin){
        pharmacieDeGardeRepository.addPharmacieDeGarde(pharmacieDeGarde,debut,fin);
        System.out.println("MVVM");
    }

}
