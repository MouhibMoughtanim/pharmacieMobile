package com.ensaj.pharmacielast.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ensaj.pharmacielast.model.Zone;
import com.ensaj.pharmacielast.repository.ZoneRepository;

import java.util.List;

public class ZoneViewModel extends ViewModel {
    private ZoneRepository zoneRepository;
    public ZoneViewModel(){
        zoneRepository = ZoneRepository.getInstance();
    }

    public LiveData<List<Zone>> getZones(){
        System.out.println("getzones");
        return zoneRepository.getZones() ;
    }

    public void searchZonesApi(int ville_id){

        System.out.println("searchzones "+ville_id );


        zoneRepository.searchZonesApi(ville_id);
    }
}
