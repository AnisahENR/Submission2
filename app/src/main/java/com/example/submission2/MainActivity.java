package com.example.submission2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.submission2.Retrofit.ApiService;
import com.example.submission2.Retrofit.ServiceGenerator;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageButton bt_search;
    ApiService service;
    Call<ResponseBody> CallBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_search = findViewById(R.id.bt_search);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DetailUser.class));
            }
        });

        getuser();
    }

    private void getuser() {

        String username = "sidiqpermana";
        service = ServiceGenerator.createService(ApiService.class);
        CallBody = service.user();

        CallBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
              //  CovidResponse model = response.body();
                if (response.isSuccessful()) {

//                    Toast.makeText(MainActivity.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, new Gson().toJson(response.body()), Toast.LENGTH_LONG).show();
//                    Snackbar.make(parent_view, "Data Gagal Disimpan", Snackbar.LENGTH_LONG).show();
//                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Data Gagal Terload", Toast.LENGTH_SHORT).show();
////                    showDialogTambah(nama_barang);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("coba gagal", t.getMessage());
            }

        });
    }
}