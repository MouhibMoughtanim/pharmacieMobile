package com.ensaj.pharmacielast.request;

import com.ensaj.pharmacielast.api.PharmacieApi;
import com.ensaj.pharmacielast.api.VilleApi;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    private static final String BASE_URL ="http://localhost:9071" ;
    private static Retrofit retrofit;


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:9071")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static VilleApi villeAPI = getRetrofitInstance().create(VilleApi.class);
    private static PharmacieApi pharmacieAPI = getRetrofitInstance().create(PharmacieApi.class);

    public static VilleApi getVilleAPI() {
         return villeAPI;
    }

    public static PharmacieApi getPharmacieAPI() {
        return pharmacieAPI;
    }
}