package com.example.submission2.Retrofit;

import com.example.submission2.Response.CariResponse;
import com.example.submission2.Response.FollowerResponse;
import com.example.submission2.Response.UsersResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("users/{username}")
//    @Headers("Authorization:f9c8af02e357697c2ffdd8801d3eb0e6c16526aa")
    Call<UsersResponse> user(@Path("username") String username,
                             @Header("Authorization") String token);
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
    @Headers("Content-Type: application/json")
    @GET("search/users/{q}")
    Call<CariResponse> cari(
            @Path("q") String username
    );

//    @GET("/users/{username}")
//    @Headers("Authorization: token <Personal Access Token>")
//    suspend fun findUserDetailByUsername(
//            @Path("username") username: String
//    ) User

//    @GET("formupdate_baranghibah/{idbarang_hibah}")
//    Call<ListBarang_Response> itembarang(
//            @Query("idbarang_hibah") String idbarang_hibah
//    );

//    @Headers("Content-Type: application/json")
    @GET("users/{username}/followers")
    Call<List<FollowerResponse>> follower(
            @Path("username") String username,
            @Header("Authorization") String token
    );

    //    https://api.github.com/users/{username}/following
    @GET("users/{username}/following")
    Call<List<FollowerResponse>> following(
            @Path("username") String username,
            @Header("Authorization") String token
    );

//    https://api.github.com/search/users?q=sidiqpermana
}
