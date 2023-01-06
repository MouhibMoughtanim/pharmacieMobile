package com.ensaj.pharmacielast.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.PharmacieDeGarde;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.repository.PharmacieDeGardeRepository;
import com.ensaj.pharmacielast.repository.PharmacieRepository;

import java.util.List;

public class PharmacieDeGardeViewModel extends ViewModel {
    private PharmacieDeGardeRepository pharmacieDeGardeRepository;
    public PharmacieDeGardeViewModel(){
        pharmacieDeGardeRepository = PharmacieDeGardeRepository.getInstance();
    }

    public LiveData<List<PharmacieDeGarde>> getPharmaciesDeGarde(){
        return pharmacieDeGardeRepository.getAllPharmaciesDeGarde();
    }

    public void searchPharmaciesDeGardeApi(){
        pharmacieDeGardeRepository.searchPharmaciesDeGarde();
    }


 public LiveData<List<PharmacieDeGarde>> getPharmaciesDeGardeById(){
        return pharmacieDeGardeRepository.getAllPharmaciesDeGardeById();
    }

    public void searchPharmaciesDeGardeByIdApi(int id){
        pharmacieDeGardeRepository.searchPharmaciesDeGardeById(id);
    }

    public void addPharmacieDeGarde(PharmacieDeGarde pharmacieDeGarde,String debut,String fin){
        pharmacieDeGardeRepository.addPharmacieDeGarde(pharmacieDeGarde,debut,fin);
        System.out.println("MVVM");
    }

}
