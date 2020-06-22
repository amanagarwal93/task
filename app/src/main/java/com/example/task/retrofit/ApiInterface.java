package com.example.task.retrofit;

import com.example.task.model.ImageData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("?key=13764025-90af241908c8af54276900443&q=yellow+flowers&image_type=photo&pretty=true")
    Call<ImageData> getCountryData();
}