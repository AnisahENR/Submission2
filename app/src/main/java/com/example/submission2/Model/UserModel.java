package com.example.submission2.Model;

public class UserModel {
    public String login;
    public String avatar_url;

    public UserModel(String login, String avatar_url){
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
