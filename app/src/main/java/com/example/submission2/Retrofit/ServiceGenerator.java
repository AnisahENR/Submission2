package com.example.submission2.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static final String BASE_URL = "https://api.github.com/users/";
    //public static final String BASE_URL = "http://192.168.42.170/api_paskot/server/" ;


    private ServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        final OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
//        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);

        retrofit2.Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addConverter(String.class, new ToStringConverter())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return builder.create(serviceClass);
    }
}