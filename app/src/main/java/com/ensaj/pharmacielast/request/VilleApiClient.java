package com.ensaj.pharmacielast.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ensaj.pharmacielast.executor.AppExecutors;
import com.ensaj.pharmacielast.model.Ville;


import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VilleApiClient {

    private static VilleApiClient instance;
    private MutableLiveData<List<Ville>> mVilles;
    private RetrieveVilles retrieveVilles;

    public static VilleApiClient getInstance() {
        if (instance == null) {
            instance = new VilleApiClient();
        }

        return instance;
    }

    private VilleApiClient() {
        mVilles = new MutableLiveData<>();

    }

    public LiveData<List<Ville>> getVilles() {
        return mVilles;
    }

    public void deleteVilleApi(int id){
        deleteVille(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Ville with id : "+id+" deleted succesfully");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Ville deleting Error : "+ t.getMessage());

            }
        });
    }
    public void addVilleApi(Ville ville){
        addVille(ville).enqueue(new Callback<Ville>() {
            @Override
            public void onResponse(Call<Ville> call, Response<Ville> response) {
                Ville ville1 = response.body();
                System.out.println("Created: "+ville1.getNom());
            }

            @Override
            public void onFailure(Call<Ville> call, Throwable t) {
                System.out.println("Ville Creating Error : "+ t.getMessage());

            }
        });
    }
    public void searchVillesApi(){

        if(retrieveVilles != null){
            retrieveVilles = null;
        }

        retrieveVilles = new RetrieveVilles();

        final Future myHandler = AppExecutors.getInstance().networkI0().submit(retrieveVilles);

        AppExecutors.getInstance().networkI0().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);

            }
        },15000, TimeUnit.MICROSECONDS);



    }


    private class RetrieveVilles implements Runnable{

        @Override
        public void run() {
                getVilles().enqueue(new Callback<List<Ville>>() {
                    @Override
                    public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                        List<Ville> villes = response.body();

                        mVilles.postValue(villes);

                    }

                    @Override
                    public void onFailure(Call<List<Ville>> call, Throwable t) {
                        System.out.println("walo"+ t.getMessage());
                        mVilles.postValue(null);
                    }
                });
        }

        private Call<List<Ville>> getVilles(){return RetrofitRequest.getVilleAPI().getVilles();}

    }
    private Call<Ville> addVille(Ville ville){
        return RetrofitRequest.getVilleAPI().createVille(ville);
    }
    private Call<Void> deleteVille(int id){
      return   RetrofitRequest.getVilleAPI().deleteVille(id);

    }

}
