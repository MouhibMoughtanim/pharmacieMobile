package com.ensaj.pharmacielast.api;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.Ville;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PharmacieApi {

    @POST("pharmacies/save")
    Call<Pharmacie> createPharmacie(@Body Pharmacie pharmacie);

    @PUT("pharmacies/update/{id}")
    Call<Pharmacie> editPharmacie(@Path("id") int id, @Body Pharmacie pharmacie);

    @GET("pharmacies/getVilleById/{id}")
    Call<Pharmacie> getPharmacieById(@Path("id") int id);

    @GET("pharmacies/all")
    Call<List<Pharmacie>> getPharmacies();

    @DELETE("pharmacies/delete/{id}")
    Call<Void> deletePharmacie(@Path("id") int id);

}
