package com.ensaj.pharmacielast.repository;

import androidx.lifecycle.LiveData;

import com.ensaj.pharmacielast.api.ZoneApi;
import com.ensaj.pharmacielast.model.Garde;
import com.ensaj.pharmacielast.model.Zone;
import com.ensaj.pharmacielast.request.GardeApiClient;
import com.ensaj.pharmacielast.request.ZoneApiClient;

import java.util.List;

public class ZoneRepository {
    private static ZoneRepository instance;
    private ZoneApiClient zoneApiClient;

    public static ZoneRepository getInstance() {
        if (instance == null) {
            instance = new ZoneRepository();

        }
        return instance;
    }

    public ZoneRepository(){
        zoneApiClient = ZoneApiClient.getInstance();
    }

    public LiveData<List<Zone>> getZones(){return zoneApiClient.getZones();}

    public void searchZonesApi(int user_id){
        zoneApiClient.getZonesApi(user_id);
    }
}
