package com.ensaj.pharmacielast.request;

import com.ensaj.pharmacielast.api.VilleApi;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    private static final String BASE_URL ="http://localhost:9071" ;
    private static Retrofit retrofit;


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://192.168.8.116:9071")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static VilleApi villeAPI = getRetrofitInstance().create(VilleApi.class);

    public static VilleApi getVilleAPI() {
         return villeAPI;
    }
}