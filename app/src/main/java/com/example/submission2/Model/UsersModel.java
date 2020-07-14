package com.example.submission2.Model;

import java.util.ArrayList;

public class UsersModel extends ArrayList<UsersModel> {

    private String name;
    private String company;
    private String blog;
    private String location;

    //permasalahan ini itu harus sesuai dengan apa yang di panggil
    public UsersModel(String name, String company, String blog, String location) {
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }
}
