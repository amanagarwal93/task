package com.example.task.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task.model.ImageData;
import com.example.task.model.ImageResponse;
import com.example.task.retrofit.ApiInterface;
import com.example.task.retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageViewModel extends ViewModel {

    public static final String TAG = ImageViewModel.class.getSimpleName();

    private MutableLiveData<ImageData> mutableImageLiveData;
    private Call<ImageData> call;

    private ApiInterface apiInterface;

    public MutableLiveData<ImageData> getMutableImageLiveData() {
        return mutableImageLiveData;
    }

    public ImageViewModel() {
        mutableImageLiveData = new MutableLiveData<>();
        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        call = apiInterface.getCountryData();
        populateList();
    }

    private void populateList() {
        call.enqueue(new Callback<ImageData>() {
            @Override
            public void onResponse(@NonNull Call<ImageData> call, @NonNull Response<ImageData> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d(TAG, "onResponse: not null " + response.body());
                        mutableImageLiveData.setValue(response.body());
                    }else{
                        Log.d(TAG, "onResponse:  is null" + response.body());
                    }
                }else{
                    Log.d(TAG, "onResponse: unsuccessful " + response.body());
                }

            }

            @Override
            public void onFailure(Call<ImageData> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
