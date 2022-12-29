package com.ensaj.pharmacielast.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ensaj.pharmacielast.model.Garde;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.repository.GardeRepository;
import com.ensaj.pharmacielast.repository.VilleRepository;

import java.util.List;

public class GardeViewModel extends ViewModel {
    private GardeRepository gardeRepository;
    public GardeViewModel(){
        gardeRepository = GardeRepository.getInstance();
    }

    public LiveData<List<Garde>> getGardes(){
        return gardeRepository.getGardes() ;
    }

    public void searchGardesApi(){
        gardeRepository.searchGardesApi();
    }

}
