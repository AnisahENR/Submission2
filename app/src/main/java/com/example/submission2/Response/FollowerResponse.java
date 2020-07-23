package com.example.submission2.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowerResponse {

    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("avatar_url")
    @Expose
    public String avatar_url;
}
