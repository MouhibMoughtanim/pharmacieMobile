package com.ensaj.pharmacielast.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.repository.VilleRepository;

import java.util.List;

public class VilleViewModel extends ViewModel {

    private VilleRepository villeRepository;
    public VilleViewModel(){
        villeRepository = VilleRepository.getInstance();
    }

    public LiveData<List<Ville>> getVilles(){
        return villeRepository.getVilles() ;
    }

    public void searchVilleApi(){
        villeRepository.searchVilleApi();
    }
    public void addVille(Ville ville){
        villeRepository.addVille(ville);
    }
    public void deleteVille(int id){villeRepository.deleteVille(id);}

}
