package com.example.submission2.Retrofit;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    //    servicegenerator2
    @GET("identitas_penginstal")
    Call<Response> IdentitasPenginstalRequest(@Query("imei") String imei);
}
