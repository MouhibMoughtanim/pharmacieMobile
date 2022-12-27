package com.ensaj.pharmacielast.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.request.VilleApiClient;

import java.util.List;

public class VilleRepository {

    private static VilleRepository instance;
    private VilleApiClient villeApiClient;

    public static VilleRepository getInstance() {
        if (instance == null) {
            instance = new VilleRepository();

        }
        return instance;
    }

    public VilleRepository(){
         villeApiClient = VilleApiClient.getInstance();
    }

    public LiveData<List<Ville>> getVilles(){return villeApiClient.getVilles();}

    public void searchVilleApi(){
        villeApiClient.searchVillesApi();
    }
    public void addVille(Ville ville){
        villeApiClient.addVilleApi(ville);
    }
    public void deleteVille(int id){villeApiClient.deleteVilleApi(id);}
}