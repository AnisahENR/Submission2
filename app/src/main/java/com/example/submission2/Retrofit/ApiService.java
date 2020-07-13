package com.example.submission2.Retrofit;

import com.example.submission2.Response.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //    servicegenerator2
    @GET("user/{username}")
    Call<UserResponse> user(@Query("username") String username);
}
