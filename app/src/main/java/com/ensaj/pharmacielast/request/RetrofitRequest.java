package com.ensaj.pharmacielast.request;

import com.ensaj.pharmacielast.api.GardeApi;
import com.ensaj.pharmacielast.api.PharmacieApi;
import com.ensaj.pharmacielast.api.PharmacieDeGardeApi;
import com.ensaj.pharmacielast.api.VilleApi;
import com.ensaj.pharmacielast.api.ZoneApi;
import com.ensaj.pharmacielast.model.Garde;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    private static final String BASE_URL ="http://localhost:9071" ;
    private static Retrofit retrofit;


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://192.168.1.120:9071")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static VilleApi villeAPI = getRetrofitInstance().create(VilleApi.class);
    private static PharmacieApi pharmacieAPI = getRetrofitInstance().create(PharmacieApi.class);
    private static GardeApi gardeAPI = getRetrofitInstance().create(GardeApi.class);
    private static PharmacieDeGardeApi pharmacieDeGardeAPI = getRetrofitInstance().create(PharmacieDeGardeApi.class);
    private static ZoneApi zoneAPI = getRetrofitInstance().create(ZoneApi.class);




    public static VilleApi getVilleAPI() {
         return villeAPI;
    }
    public static ZoneApi getZoneAPI() {
        return zoneAPI;
    }

    public static GardeApi getGardeAPI() {
        return gardeAPI;
    }
    public static PharmacieDeGardeApi getPharmacieDeGardeAPI() {
        return pharmacieDeGardeAPI;
    }
    public static PharmacieApi getPharmacieAPI() {
        return pharmacieAPI;
    }
}