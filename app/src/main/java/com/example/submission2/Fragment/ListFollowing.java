package com.example.submission2.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission2.Adapter.AdapterListFollow;
import com.example.submission2.Model.FollowModel;
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
    ArrayList<FollowModel> listuser;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_01, container, false);
//        View view = inflater.inflate(R.layout.fragment_01_6, container, false);

        View view = inflater.inflate(R.layout.fragment_follower, container, false);

        listuser = new ArrayList<FollowModel>();

        getuser2();
        recyclerView = (RecyclerView) view.findViewById(R.id.daftar_follow);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setHasFixedSize(true);
        setAdapter();

        return view;
    }

    private void setAdapter() {
        //set data and list adapter
        mAdapter = new AdapterListFollow(getContext(), listuser);
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
////                startActivity(detail_event);
//                Snackbar.make(parent_view, "Item " + obj.getJudul_event() + " clicked", Snackbar.LENGTH_SHORT).show();
//            }
//        });
    }

    private void getuser2() {

        String username = "sidiqpermana";
        service = ServiceGenerator.createService(ApiService.class);
        Call<List<FollowerResponse>> CallBody3;
        CallBody3 = service.following(username);

        CallBody3.enqueue(new Callback<List<FollowerResponse>>() {
            @Override
            public void onResponse(Call<List<FollowerResponse>> call, Response<List<FollowerResponse>> response) {
                //  CovidResponse model = response.body();
                List<FollowerResponse> data = response.body();
                if (response.isSuccessful()) {
                    for (int i = 0; i < data.size(); i++) {
                        listuser.add(new FollowModel(data.get(i).login, data.get(i).avatar_url));
                    }
                    mAdapter.notifyDataSetChanged();
//                    Toast.makeText(MainActivity.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(MainActivity.this, new Gson().toJson(response.body()), Toast.LENGTH_LONG).show();
//                    Snackbar.make(parent_view, "Data Gagal Disimpan", Snackbar.LENGTH_LONG).show();
//                    finish();
                } else {
                    Toast.makeText(getContext(), response.message() + "  datasalah", Toast.LENGTH_SHORT).show();
////                    showDialogTambah(nama_barang);

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
