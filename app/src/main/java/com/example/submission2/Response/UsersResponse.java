package com.example.submission2.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsersResponse {
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("id")
    @Expose
    public String id;
//    public String node_id;
    public String avatar_url;
//    public String gravatar_id;
//    public String url;
//    public String html_url;
    public String followers_url;
    public String following_url;
//    public String gists_url;
//    public String starred_url;
//    public String subscriptions_url;
//    public String organizations_url;
//    public String repos_url;
//    public String events_url;
//    public String received_events_url;
//    public String type;
//    public String site_admin;
    public String name;
    public String company;
    public String blog;
    public String location;
    public String email;
    public String hireable;
    public String bio;
//    public String twitter_username;
//    public String public_repos;
//    public String public_gists;
    public String followers;
    public String following;
//    public String created_at;
//    public String updated_at;
}
