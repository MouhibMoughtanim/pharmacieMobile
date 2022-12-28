package com.ensaj.pharmacielast.request;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.Ville;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacieApiClient {

    private MutableLiveData<List<Pharmacie>> mPharmacies;
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

    }
    public LiveData<List<Pharmacie>> getPharmacies() {
        return mPharmacies;
    }


    // Retrofit implementation
    public void addPharmacieApi(Pharmacie pharmacie){
        addPharmacie(pharmacie).enqueue(new Callback<Pharmacie>() {
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

    //Retrofit calls
    private Call<List<Pharmacie>> getPharmacie(){
        return RetrofitRequest.getPharmacieAPI().getPharmacies();
    }
    private Call<Pharmacie> addPharmacie(Pharmacie pharmacie){
        return RetrofitRequest.getPharmacieAPI().createPharmacie(pharmacie);
    }
}
