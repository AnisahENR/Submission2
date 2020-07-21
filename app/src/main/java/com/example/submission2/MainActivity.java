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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.submission2.Adapter.AdapterListUser;
import com.example.submission2.Model.UserModel;
import com.example.submission2.Response.CariResponse;
import com.example.submission2.Response.FollowerResponse;
import com.example.submission2.Response.UsersResponse;
import com.example.submission2.Retrofit.ApiService;
import com.example.submission2.Retrofit.ServiceGenerator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageButton bt_search;
    ApiService service;
    Call<CariResponse> CallBody;
    Call<UsersResponse> CallBody2;
    EditText cari;
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
//
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//            searchView.setQueryHint(getResources().getString(R.string.hint_search));
            cari2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                /*
                Gunakan method ini ketika search selesai atau OK
                 */
                @Override
                public boolean onQueryTextSubmit(String query) {
//                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                    getuser(query);
                    return true;
                }

                /*
                Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
                 */
                @Override
                public boolean onQueryTextChange(String newText) {
                    String text = newText;
//                    mAdapter.filter(text);
//                    return false;
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

//         String q = "sidiqpermana";
        String token = "f9c8af02e357697c2ffdd8801d3eb0e6c16526aa";
        service = ServiceGenerator.createService(ApiService.class);
        CallBody = service.cari(q, token);

        CallBody.enqueue(new Callback<CariResponse>() {
            @Override
            public void onResponse(Call<CariResponse> call, Response<CariResponse> response) {
                progressBar.setVisibility(View.VISIBLE);
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

    private void getuser2() {
        progressBar.setVisibility(View.VISIBLE);
        String username = "sidiqpermana";
        String token = "f9c8af02e357697c2ffdd8801d3eb0e6c16526aa";
        service = ServiceGenerator.createService(ApiService.class);
        Call<List<FollowerResponse>> CallBody3;
        CallBody3 = service.follower(username, token);

        CallBody3.enqueue(new Callback<List<FollowerResponse>>() {
            @Override
            public void onResponse(Call<List<FollowerResponse>> call, Response<List<FollowerResponse>> response) {
                //  CovidResponse model = response.body();
                List<FollowerResponse> datares = response.body();

                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < datares.size(); i++) {
                        listuser.add(new UserModel(datares.get(i).login, datares.get(i).avatar_url));
                    }
                    mAdapter.notifyDataSetChanged();

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<FollowerResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}