package com.example.asus.pict.pembeli;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.asus.pict.Adapter.EtalaseListAdapter;
import com.example.asus.pict.ListUserPosting;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.EtalaseRes;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EtalaseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    EtalaseRes userPostingList;
    EtalaseListAdapter adapter;
    ProgressDialog pDialog;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etalase);
        recyclerView = findViewById(R.id.rv_etalase);

        BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
        Call<EtalaseRes> call = service.getEtalase(7);
        call.enqueue(new Callback<EtalaseRes>() {
            @Override
            public void onResponse(Call<EtalaseRes> call, Response<EtalaseRes> response) {
                if (!response.body().getError()){
                    gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    adapter = new EtalaseListAdapter(EtalaseActivity.this,response.body());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<EtalaseRes> call, Throwable t) {

            }
        });
    }
}