package com.example.submission2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.submission2.Adapter.AdapterListFollow;
import com.example.submission2.Model.FollowModel;
import com.example.submission2.Response.FollowerResponse;
import com.example.submission2.Response.UsersResponse;
import com.example.submission2.Retrofit.ApiService;
import com.example.submission2.Retrofit.ServiceGenerator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.submission2.ui.main.SectionsPagerAdapter;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUser extends AppCompatActivity {
    ApiService service;
    Call<UsersResponse> CallBody;
    String s_nama, s_company, s_blog, s_alamat;
    CircularImageView ava;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        progressBar = findViewById(R.id.progressBar);
        tabs.setupWithViewPager(viewPager);

        initToolbar();
        String username = "sidiqpermana";
        getdetail_user(username);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_detail_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void ubah_findview() {
        ((TextView) findViewById(R.id.nama_detail)).setText(s_nama);
        ((TextView) findViewById(R.id.company)).setText(s_company);
        ((TextView) findViewById(R.id.blog)).setText(s_blog);
        ((TextView) findViewById(R.id.location)).setText(s_alamat);
        ava = findViewById(R.id.avatar);
    }

    private void getdetail_user(String username) {
        progressBar.setVisibility(View.VISIBLE);
        service = ServiceGenerator.createService(ApiService.class);
        String token = "f9c8af02e357697c2ffdd8801d3eb0e6c16526aa";
        CallBody = service.user(username,token);

        CallBody.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                UsersResponse data = response.body();
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);

                    s_nama = data.login;
                    s_company = data.company;
                    s_blog = data.blog;
                    s_alamat = data.location;

                    ubah_findview();
                    Glide.with(getApplication())
                            .load(data.avatar_url)
                            .fitCenter() // menyesuaikan ukuran imageview
                            .crossFade() // animasi
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(ava);

                } else {
                    Toast.makeText(DetailUser.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(DetailUser.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.icon_setting) {
            Intent i = new Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS);
            startActivity(i);
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}