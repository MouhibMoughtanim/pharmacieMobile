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

    @POST("pharmacies/add/{user_id}")
    Call<Pharmacie> createPharmacie(@Body Pharmacie pharmacie,@Path("user_id") int user_id);

    @PUT("pharmacies/updatePharmacie/id={id}")
    Call<Pharmacie> editPharmacie(@Path("id") int id, @Body Pharmacie pharmacie);

    @GET("pharmacies/pharmacie/id={id}")
    Call<Pharmacie> getPharmacieById(@Path("id") int id);

    @GET("pharmacies/pharmacie/user_id={id}")
    Call<Pharmacie> getPharmacieByUserId(@Path("id") int id);

    @GET("pharmacies/all")
    Call<List<Pharmacie>> getPharmacies();

    @DELETE("pharmacies/deletePharmacie/id={id}")
    Call<Void> deletePharmacie(@Path("id") int id);

}
