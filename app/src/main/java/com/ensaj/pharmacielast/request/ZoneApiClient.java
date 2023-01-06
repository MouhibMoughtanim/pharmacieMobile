package com.ensaj.pharmacielast.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ensaj.pharmacielast.model.Garde;
import com.ensaj.pharmacielast.model.Zone;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZoneApiClient {
    private MutableLiveData<List<Zone>> mZones;
    private static ZoneApiClient instance;

    //singleton
    public static ZoneApiClient getInstance() {
        if (instance == null) {
            instance = new ZoneApiClient();
        }

        return instance;
    }
    //constructor and getters
    private ZoneApiClient() {
        mZones = new MutableLiveData<>();

    }
    public LiveData<List<Zone>> getZones() {
        return mZones;
    }


    // Retrofit implementation
    public void getZonesApi(int ville_id){
        getZone(ville_id).enqueue(new Callback<List<Zone>>() {
            @Override
            public void onResponse(Call<List<Zone>> call, Response<List<Zone>> response) {
                List<Zone> zones = response.body();
                System.out.println("ta kifach");

                mZones.postValue(zones);
            }

            @Override
            public void onFailure(Call<List<Zone>> call, Throwable t) {
                System.out.println("walo"+ t.getMessage());
                mZones.postValue(null);
            }
        });
    }

    //Retrofit calls
    private Call<List<Zone>> getZone(int ville_id){
        return RetrofitRequest.getZoneAPI().getAllZonesByVille(ville_id);
    }
}
