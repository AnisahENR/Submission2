package com.example.submission2.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("login")
    @Expose
    public String login;
    public String id;
    public String node_id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;

}
