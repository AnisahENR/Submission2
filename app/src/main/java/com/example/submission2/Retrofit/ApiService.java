package com.example.submission2.Retrofit;

import com.example.submission2.Response.CariResponse;
import com.example.submission2.Response.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //    servicegenerator2
    @GET("users/{username}")
    Call<Users> user( @Path("username") String username);
//    @Query("username") String username

    @GET("search/users/{q}")
    Call<CariResponse>cari_users(@Query("q") String q);

//    https://api.github.com/search/users?q=sidiqpermana
}
