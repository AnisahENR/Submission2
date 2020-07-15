package com.example.submission2.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FollowerResponse {

//    public ArrayList<followers> followers;
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("avatar_url")
    @Expose
    public String avatar_url;

//    public class followers{
//
//
//    }
}
