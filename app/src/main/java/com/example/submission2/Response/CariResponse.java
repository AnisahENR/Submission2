package com.example.submission2.response;

import java.util.List;

public class CariResponse {

    private long totalCount;
    private boolean incompleteResults;
    public List<items> items;

    public class items {
        public String login;
        public String follower_url;
        public String avatar_url;
    }
}
