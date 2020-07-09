package com.example.submission2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.submission2.R;

public class ListFollowing extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_01, container, false);
//        View view = inflater.inflate(R.layout.fragment_01_6, container, false);

        View view = null;

        return view = inflater.inflate(R.layout.fragment_follower, container, false);


    }
}
