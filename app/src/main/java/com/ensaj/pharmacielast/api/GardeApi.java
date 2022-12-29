package com.ensaj.pharmacielast.api;

import com.ensaj.pharmacielast.model.Garde;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GardeApi {

    @GET("gardes/all")
    Call<List<Garde>> getGardes();

}
