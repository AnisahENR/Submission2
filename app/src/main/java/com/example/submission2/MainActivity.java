package com.example.submission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.submission2.Adapter.AdapterListUser;
import com.example.submission2.Model.UsersModel;
import com.example.submission2.Response.CariResponse;
import com.example.submission2.Response.Users;
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
    EditText cari;
    SearchView cari2;
    private RecyclerView recyclerView;
    private AdapterListUser mAdapter;
    ArrayList<UsersModel> listuser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        cari = findViewById(R.id.et_search);
        cari2 = findViewById(R.id.cari);
        listuser = new ArrayList<UsersModel>();

//        bt_search = findViewById(R.id.bt_search);
//        bt_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, DetailUser.class));
//            }
//        });
//        pencarian();
//        recyclerView = (RecyclerView) findViewById(R.id.list_user);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        setAdapter();

        getuser();
    }

    public void pencarian(){
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
//                    getuser(query);
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

        // on item list clicked
//        mAdapter.setOnItemClickListener(new AdapterListEvent.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, ModelEvent obj, int position) {
//
//
////                Intent detail_event = new Intent(List_event.this,Detail_berita.class);
////                ModelEvent listdetail = event12.get(position);
////                detail_event.putExtra("id_event",listdetail.getId_event());
////                detail_event.putExtra("judul_event",listdetail.getJudul_event());
////                detail_event.putExtra("deskripsi",listdetail.getDeskripsi());
////                detail_event.putExtra("nama_gambar",listdetail.getNama_gambar());
////                startActivity(detail_event);
//                Snackbar.make(parent_view, "Item " + obj.getJudul_event() + " clicked", Snackbar.LENGTH_SHORT).show();
//            }
//        });
    }

    private void getuser() {

        String q = "sidiqpermana";
        service = ServiceGenerator.createService(ApiService.class);
        CallBody = service.cari_users(q);

        CallBody.enqueue(new Callback<CariResponse>() {
            @Override
            public void onResponse(Call<CariResponse> call, Response<CariResponse> response) {
              //  CovidResponse model = response.body();
                if (response.isSuccessful()) {

//                    Toast.makeText(MainActivity.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, new Gson().toJson(response.body()), Toast.LENGTH_LONG).show();
//                    Snackbar.make(parent_view, "Data Gagal Disimpan", Snackbar.LENGTH_LONG).show();
//                    finish();
                } else {
                    Toast.makeText(MainActivity.this, response.message()+"datasalah", Toast.LENGTH_SHORT).show();
////                    showDialogTambah(nama_barang);

                }
            }

            @Override
            public void onFailure(Call<CariResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("coba gagal", t.getMessage());
            }

        });
    }
}