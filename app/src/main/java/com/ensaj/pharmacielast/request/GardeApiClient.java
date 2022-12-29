package com.ensaj.pharmacielast.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ensaj.pharmacielast.api.GardeApi;
import com.ensaj.pharmacielast.model.Garde;
import com.ensaj.pharmacielast.model.Pharmacie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GardeApiClient {
    private MutableLiveData<List<Garde>> mGardes;
    private static GardeApiClient instance;

    //singleton
    public static GardeApiClient getInstance() {
        if (instance == null) {
            instance = new GardeApiClient();
        }

        return instance;
    }
    //constructor and getters
    private GardeApiClient() {
        mGardes = new MutableLiveData<>();

    }
    public LiveData<List<Garde>> getGardes() {
        return mGardes;
    }


    // Retrofit implementation
    public void getGardesApi(){
        getGarde().enqueue(new Callback<List<Garde>>() {
            @Override
            public void onResponse(Call<List<Garde>> call, Response<List<Garde>> response) {
                List<Garde> gardes = response.body();

                mGardes.postValue(gardes);
            }

            @Override
            public void onFailure(Call<List<Garde>> call, Throwable t) {
                System.out.println("walo"+ t.getMessage());
                mGardes.postValue(null);
            }
        });
    }

    //Retrofit calls
    private Call<List<Garde>> getGarde(){
        return RetrofitRequest.getGardeAPI().getGardes();
    }
}
