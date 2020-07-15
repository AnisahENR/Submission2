package com.example.submission2.Retrofit;

import com.example.submission2.Response.CariResponse;
import com.example.submission2.Response.FollowerResponse;
import com.example.submission2.Response.UsersResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //    servicegenerator2
    @GET("users/{username}")
    Call<UsersResponse> user(@Path("username") String username);
//    @Query("username") String username

    @GET("search/users?q=sidiqpermana")
    Call<CariResponse> cari_users();

//    @GET("formupdate_kelompok/{iddaftar_kelompok}/{idmaster_kelompok}")
//    Call<KelasUsaha_Response> form_kelompok(
//            @Query("iddaftar_kelompok") String iddaftar_kelompok,
//            @Query("idmaster_kelompok") String idmaster_kelompok
//    );

//    @GET("users/{q}")
//    Call<CariResponse> cari(
//            @Query("q") String q);

    @GET("users/{q}")
    Call<CariResponse> cari(
            @Query("q") String q
    );

    // https://api.github.com/users/sidiqpermana/followers
//    @Headers("Content-Type: application/json")
    @GET("users/{username}/followers")
    Call<List<FollowerResponse>> follower(
            @Path("username") String username
    );
//    https://api.github.com/users/{username}/following
    @GET("users/{username}/following")
    Call<List<FollowerResponse>> following (
            @Path("username") String username
    );

//    https://api.github.com/search/users?q=sidiqpermana
}
