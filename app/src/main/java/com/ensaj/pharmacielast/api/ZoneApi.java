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
    @POST("zones/add")
    Call<Zone> createZone(@Body Zone zone);

    @PUT("zones/updateZone/id={id}")
    Call<Zone> editZone(@Path("id") int id, @Body Zone zone);

    @GET("zones/zone/id={id}")
    Call<Zone> getZoneById(@Path("id") int id);

    @GET("zones/all")
    Call<List<Zone>> getZones();

    @DELETE("zones/deleteZone/id={id}")
    Call<Void> deleteZone(@Path("id") int id);

    @GET("zones/zone/ville={id}")
    Call<List<Zone>> getAllZonesByVille(@Path("id") int id);




}
