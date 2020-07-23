package com.example.submission2.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsersResponse {
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("id")
    @Expose
    public String id;
    public String avatar_url;
    public String name;
    public String company;
    public String blog;
    public String location;
}
