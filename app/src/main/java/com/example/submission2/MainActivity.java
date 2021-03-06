package com.example.submission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.submission2.adapter.AdapterListUser;
import com.example.submission2.model.UserModel;
import com.example.submission2.response.CariResponse;
import com.example.submission2.retrofit.ApiService;
import com.example.submission2.retrofit.ServiceGenerator;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiService service;
    Call<CariResponse> CallBody;
    SearchView cari2;
    private RecyclerView recyclerView;
    private AdapterListUser mAdapter;
    ArrayList<UserModel> listuser;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cari2 = findViewById(R.id.cari);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        listuser = new ArrayList<UserModel>();
        cari2.clearFocus();
        pencarian(); // memanggil method pencarian

        // memasukkan data hasil cari kedalam recycler view
        recyclerView = (RecyclerView) findViewById(R.id.list_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        setAdapter();

    }

    public void pencarian() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            cari2.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//            cari2.setQueryHint(getResources().getString(R.string.hint_search));
            cari2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                /*
                Gunakan method ini ketika search selesai atau OK
                 */
                @Override
                public boolean onQueryTextSubmit(String query) {
                    getuser(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    String text = newText;
                    mAdapter.filter(text);
                    return false;
                }
            });

        }
    }

    private void setAdapter() {
        //set data and list adapter
        mAdapter = new AdapterListUser(this, listuser);
        recyclerView.setAdapter(mAdapter);
    }

    private void getuser(String q) {
        progressBar.setVisibility(View.VISIBLE);
        String token = BuildConfig.GITHUB_TOKEN;
        service = ServiceGenerator.createService(ApiService.class);
        CallBody = service.cari(q, token);

        CallBody.enqueue(new Callback<CariResponse>() {
            @Override
            public void onResponse(Call<CariResponse> call, Response<CariResponse> response) {
                CariResponse datares = response.body();
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < datares.items.size(); i++) {
                        listuser.add(new UserModel(datares.items.get(i).login, datares.items.get(i).avatar_url));
                    }
                    mAdapter.notifyDataSetChanged();
//                    Log.d("isi data : ", new Gson().toJson(response.body()));
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, new Gson().toJson(response.body()) + " \n" + response.message(), Toast.LENGTH_SHORT).show();
                    Log.d("isi data : ", new Gson().toJson(response.body()));

                }

            }

            @Override
            public void onFailure(Call<CariResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}