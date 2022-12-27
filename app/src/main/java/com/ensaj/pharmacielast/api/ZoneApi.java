package com.ensaj.pharmacielast.api;


import com.ensaj.pharmacielast.model.Zone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ZoneApi {
    @POST("zones/save")
    Call<Zone> createZone(@Body Zone zone);

    @PUT("zones/update/{id}")
    Call<Zone> editZone(@Path("id") int id, @Body Zone zone);

    @GET("zones/getZoneById/{id}")
    Call<Zone> getZoneById(@Path("id") int id);

    @GET("zones/all")
    Call<List<Zone>> getZones();

    @DELETE("zones/delete/{id}")
    Call<Void> deleteZone(@Path("id") int id);


}
