package com.example.submission2.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission2.Adapter.AdapterListFollow;
import com.example.submission2.Model.UserModel;
import com.example.submission2.R;
import com.example.submission2.Response.FollowerResponse;
import com.example.submission2.Retrofit.ApiService;
import com.example.submission2.Retrofit.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFollowing extends Fragment {

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

        listuser = new ArrayList<UserModel>();
        progressBar = view.findViewById(R.id.progressBar);

        username = getArguments().getString("username");
        getfollowing(username);
        recyclerView = (RecyclerView) view.findViewById(R.id.daftar_follow);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setHasFixedSize(true);
        setAdapter();

        return view;
    }

    private void setAdapter() {
        //set data and list adapter
        mAdapter = new AdapterListFollow(getContext(), listuser);
        recyclerView.setAdapter(mAdapter);
    }

    private void getfollowing(String username) {
        progressBar.setVisibility(View.VISIBLE);
        String token = "f9c8af02e357697c2ffdd8801d3eb0e6c16526aa";
        service = ServiceGenerator.createService(ApiService.class);
        Call<List<FollowerResponse>> CallBody3;
        CallBody3 = service.following(username,token);

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
                    Toast.makeText(getContext(), response.message() + "  datasalah", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<FollowerResponse>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("coba gagal", t.getMessage());
            }

        });
    }
}
