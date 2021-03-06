package com.example.submission2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission2.BuildConfig;
import com.example.submission2.adapter.AdapterListFollow;
import com.example.submission2.model.UserModel;
import com.example.submission2.R;
import com.example.submission2.response.FollowerResponse;
import com.example.submission2.retrofit.ApiService;
import com.example.submission2.retrofit.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFollower extends Fragment {

    ApiService service;
    private RecyclerView recyclerView;
    private AdapterListFollow mAdapter;
    ArrayList<UserModel> listuser;
    private ProgressBar progressBar;
    String username;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_follower, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listuser = new ArrayList<UserModel>();
        progressBar = view.findViewById(R.id.progressBar);
        username = getArguments().getString("username");
        getFollower(username);
        recyclerView = (RecyclerView) view.findViewById(R.id.daftar_follow);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setHasFixedSize(true);
        setAdapter();

    }

    private void setAdapter() {
        //set data and list adapter
        mAdapter = new AdapterListFollow(getContext(), listuser);
        recyclerView.setAdapter(mAdapter);
    }

    private void getFollower(String username) {
        progressBar.setVisibility(View.VISIBLE);
        String token = BuildConfig.GITHUB_TOKEN;
        service = ServiceGenerator.createService(ApiService.class);
        Call<List<FollowerResponse>> CallBody3;
        CallBody3 = service.follower(username, token);

        CallBody3.enqueue(new Callback<List<FollowerResponse>>() {
            @Override
            public void onResponse(Call<List<FollowerResponse>> call, Response<List<FollowerResponse>> response) {
                List<FollowerResponse> data = response.body();
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < data.size(); i++) {
                        listuser.add(new UserModel(data.get(i).login, data.get(i).avatar_url));
                    }
                    mAdapter.notifyDataSetChanged();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowerResponse>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
