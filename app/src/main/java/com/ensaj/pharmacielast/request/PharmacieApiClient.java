package com.ensaj.pharmacielast.request;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ensaj.pharmacielast.Main2Activity;
import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.Ville;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacieApiClient {

    private MutableLiveData<List<Pharmacie>> mPharmacies;
    private MutableLiveData<Pharmacie> mPharmacieByUserId;
    private MutableLiveData<List<Pharmacie>> mPharmacieByZoneId;

    private static PharmacieApiClient instance;

    //singleton
    public static PharmacieApiClient getInstance() {
        if (instance == null) {
            instance = new PharmacieApiClient();
        }

        return instance;
    }
    //constructor and getters
    private PharmacieApiClient() {
        mPharmacies = new MutableLiveData<>();
        mPharmacieByUserId = new MutableLiveData<>();
        mPharmacieByZoneId = new MutableLiveData<>();
    }
    public LiveData<List<Pharmacie>> getPharmacies() {
        return mPharmacies;
    }
    public LiveData<Pharmacie> getPharmacieByUserId() {
        return mPharmacieByUserId;
    }
    public LiveData<List<Pharmacie>> getPharmaciesByZoneId() {
        return mPharmacieByZoneId;
    }


    // Retrofit implementation
    public void addPharmacieApi(Pharmacie pharmacie,int user_id){
        addPharmacie(pharmacie,user_id).enqueue(new Callback<Pharmacie>() {
            @Override
            public void onResponse(Call<Pharmacie> call, Response<Pharmacie> response) {
                Pharmacie pharmacie1 = response.body();


                System.out.println("Created: "+pharmacie1.getNom());
            }

            @Override
            public void onFailure(Call<Pharmacie> call, Throwable t) {
                System.out.println("Ville Creating Error : "+ t.getMessage());

            }
        });
    }
    public void getPharmacieApi(){
        getPharmacie().enqueue(new Callback<List<Pharmacie>>() {
            @Override
            public void onResponse(Call<List<Pharmacie>> call, Response<List<Pharmacie>> response) {
                List<Pharmacie> pharmacies = response.body();

                mPharmacies.postValue(pharmacies);
            }

            @Override
            public void onFailure(Call<List<Pharmacie>> call, Throwable t) {
                System.out.println("walo"+ t.getMessage());
                mPharmacies.postValue(null);
            }
        });
    }

    public void getPharmacieByUserIdApi(int user_id){
        getPharmacieByUserId(user_id).enqueue(new Callback<Pharmacie>() {
            @Override
            public void onResponse(Call<Pharmacie> call, Response<Pharmacie> response) {
                Pharmacie pharmacie = response.body();
                mPharmacieByUserId.postValue(pharmacie);
            }

            @Override
            public void onFailure(Call<Pharmacie> call, Throwable t) {
               mPharmacieByUserId.postValue(null);
            }
        });
    }

    public void getPharmacieByZoneIdApi(int zone_id) {

        getPharmaciesByZoneId(zone_id).enqueue(new Callback<List<Pharmacie>>() {
            @Override
            public void onResponse(Call<List<Pharmacie>> call, Response<List<Pharmacie>> response) {
                List<Pharmacie> pharmacies = response.body();
                System.out.println("hhhhhhh");
                mPharmacieByZoneId.postValue(pharmacies);
            }

            @Override
            public void onFailure(Call<List<Pharmacie>> call, Throwable t) {
                 mPharmacieByZoneId.postValue(null);
            }
        });
    }

        //Retrofit calls
    private Call<List<Pharmacie>> getPharmacie(){
        return RetrofitRequest.getPharmacieAPI().getPharmacies();
    }
    private Call<Pharmacie> getPharmacieByUserId(int user_id){
        return RetrofitRequest.getPharmacieAPI().getPharmacieByUserId(user_id);
    }
    private Call<Pharmacie> addPharmacie(Pharmacie pharmacie,int user_id){
        return RetrofitRequest.getPharmacieAPI().createPharmacie(pharmacie,user_id);
    }
    private Call<List<Pharmacie>> getPharmaciesByZoneId(int zone_id){
        return RetrofitRequest.getPharmacieAPI().getPharmaciesByZoneId(zone_id);
    }
}
