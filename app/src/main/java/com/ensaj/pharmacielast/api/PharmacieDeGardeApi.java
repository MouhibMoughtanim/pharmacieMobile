package com.ensaj.pharmacielast.api;

import com.ensaj.pharmacielast.model.PharmacieDeGarde;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PharmacieDeGardeApi {

    @POST("pharmaciesDeGarde/add/{debut}/{fin}")
    Call<PharmacieDeGarde> createPharmacieDeGarde(@Body PharmacieDeGarde pharmacieDeGarde, @Path("debut") String debut,@Path("fin") String fin);
}
