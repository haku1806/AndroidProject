package com.haku1806.service;

import com.haku1806.model.ModelCommom;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroService {
    @GET("data.json")
    Call<ModelCommom> getAll();
}
