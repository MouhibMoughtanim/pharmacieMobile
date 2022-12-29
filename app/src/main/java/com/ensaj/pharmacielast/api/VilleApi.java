package com.ensaj.pharmacielast.api;


import android.os.Parcelable;

import com.ensaj.pharmacielast.model.Ville;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VilleApi  {
    @POST("villes/add")
    Call<Ville> createVille(@Body Ville ville);

    @PUT("villes/updateVille/id={id}")
    Call<Ville> editVille(@Path("id") int id, @Body Ville ville);

    @GET("villes/ville/id={id}")
    Call<Ville> getVilleById(@Path("id") int id);

    @GET("villes/all")
    Call<List<Ville>> getVilles();

    @DELETE("villes/deleteVille/id={id}")
    Call<Void> deleteVille(@Path("id") int id);


}
