package com.example.submission2.Response;

import java.util.ArrayList;

public class CariResponse {

    public String total_count;
    public String incomplete_results;
    public ArrayList<items> items;

    public class items{
        public String login;
        public String follower_url;
        public String avatar_url;
    }
}
