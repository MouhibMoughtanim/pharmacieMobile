package com.ensaj.pharmacielast.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.PharmacieDeGarde;
import com.ensaj.pharmacielast.model.Ville;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacieDeGardeApiClient {

    private MutableLiveData<List<PharmacieDeGarde>> mPharmaciesDeGarde;
    private static PharmacieDeGardeApiClient instance;

    //singleton
    public static PharmacieDeGardeApiClient getInstance() {
        if (instance == null) {
            instance = new PharmacieDeGardeApiClient();
        }

        return instance;
    }
    //constructor and getters
    private PharmacieDeGardeApiClient() {
        mPharmaciesDeGarde = new MutableLiveData<>();

    }
    public LiveData<List<PharmacieDeGarde>> getPharmaciesDeGarde() {
        return mPharmaciesDeGarde;
    }


    // Retrofit implementation
    public void addPharmacieDeGardeApi(PharmacieDeGarde pharmacieDeGarde,String debut,String fin){
        System.out.println(pharmacieDeGarde.getPharmacie().getId() + " ID PHARMACIE");
        System.out.println(pharmacieDeGarde.getGarde().getIdGarde() + " ID GARDE");

        addPharmacieDeGarde(pharmacieDeGarde,debut,fin).enqueue(new Callback<PharmacieDeGarde>() {
            @Override
            public void onResponse(Call<PharmacieDeGarde> call, Response<PharmacieDeGarde> response) {
                PharmacieDeGarde pharmacieDeGarde1 = response.body();
              //  System.out.println("Created: "+pharmacieDeGarde1.getDateFin());
            }

            @Override
            public void onFailure(Call<PharmacieDeGarde> call, Throwable t) {
                System.out.println("Ville Creating Error : "+ t.getMessage());

            }
        });
    }
   public void getPharmaciesDeGardeApi(){
        getPharmacieDeGarde().enqueue(new Callback<List<PharmacieDeGarde>>() {
            @Override
            public void onResponse(Call<List<PharmacieDeGarde>> call, Response<List<PharmacieDeGarde>> response) {
                List<PharmacieDeGarde> pharmaciesDeGarde = response.body();

                mPharmaciesDeGarde.postValue(pharmaciesDeGarde);
            }

            @Override
            public void onFailure(Call<List<PharmacieDeGarde>> call, Throwable t) {
                System.out.println("walo"+ t.getMessage());
                mPharmaciesDeGarde.postValue(null);
            }
        });
    }


    //Retrofit calls
    private Call<List<PharmacieDeGarde>> getPharmacieDeGarde(){
        return RetrofitRequest.getPharmacieDeGardeAPI().getAllPharmaciesDeGarde();
    }
    private Call<PharmacieDeGarde> addPharmacieDeGarde(PharmacieDeGarde pharmacieDeGarde,String debut,String fin){
        return RetrofitRequest.getPharmacieDeGardeAPI().createPharmacieDeGarde(pharmacieDeGarde,debut,fin);
    }
}
