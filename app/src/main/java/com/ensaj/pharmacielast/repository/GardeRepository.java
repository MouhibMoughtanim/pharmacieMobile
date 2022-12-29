package com.ensaj.pharmacielast.repository;

import androidx.lifecycle.LiveData;

import com.ensaj.pharmacielast.model.Garde;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.request.GardeApiClient;
import com.ensaj.pharmacielast.request.VilleApiClient;

import java.util.List;

public class GardeRepository {
    private static GardeRepository instance;
    private GardeApiClient gardeApiClient;

    public static GardeRepository getInstance() {
        if (instance == null) {
            instance = new GardeRepository();

        }
        return instance;
    }

    public GardeRepository(){
        gardeApiClient = GardeApiClient.getInstance();
    }

    public LiveData<List<Garde>> getGardes(){return gardeApiClient.getGardes();}

    public void searchGardesApi(){
        gardeApiClient.getGardesApi();
    }

}
