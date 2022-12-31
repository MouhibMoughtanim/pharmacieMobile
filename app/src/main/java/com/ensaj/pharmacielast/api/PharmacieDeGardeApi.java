package com.ensaj.pharmacielast.api;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.PharmacieDeGarde;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PharmacieDeGardeApi {

    @POST("pharmaciesDeGarde/add/{debut}/{fin}")
    Call<PharmacieDeGarde> createPharmacieDeGarde(@Body PharmacieDeGarde pharmacieDeGarde, @Path("debut") String debut,@Path("fin") String fin);

    @GET("pharmaciesDeGarde/all")
    Call<List<PharmacieDeGarde>> getAllPharmaciesDeGarde();

}
