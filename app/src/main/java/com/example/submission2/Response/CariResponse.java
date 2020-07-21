package com.example.submission2.Response;

import java.util.List;

public class CariResponse {

    //    public String total_count;
//    public String incomplete_results;
    private long totalCount;
    private boolean incompleteResults;
//    private List<User> items;
    public List<items> items;

    public class items {
        public String login;
        public String follower_url;
        public String avatar_url;
    }
}
