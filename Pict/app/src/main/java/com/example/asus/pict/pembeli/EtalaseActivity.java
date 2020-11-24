package com.example.asus.pict.pembeli;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.pict.Adapter.EtalaseListAdapter;
import com.example.asus.pict.ListUserPosting;
import com.example.asus.pict.Petani.TambahProdukActivity;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.EtalaseRes;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EtalaseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    EtalaseRes userPostingList;
    EtalaseListAdapter adapter;
    TextView tvnama_toko,tv_desc;
    ProgressDialog pDialog;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etalase);
        recyclerView = findViewById(R.id.rv_etalase);
        tvnama_toko = findViewById(R.id.tv_nama_toko);
        tv_desc = findViewById(R.id.tv_desc_toko);


        pDialog = new ProgressDialog(EtalaseActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        pDialog.show();
        Bundle bundle = new Bundle();
        int id = bundle.getInt("id",0);
        BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
        Call<EtalaseRes> call = service.getEtalase(id);
        call.enqueue(new Callback<EtalaseRes>() {
            @Override
            public void onResponse(Call<EtalaseRes> call, Response<EtalaseRes> response) {
                if (!response.body().getError()){
                    JsonParser parser = new JsonParser();
                    final JsonObject jsonObject = (JsonObject) parser.parse(response.body().getToko());
                    tvnama_toko.setText(jsonObject.get("nama_toko").getAsString());
                    tv_desc.setText(jsonObject.get("deskripsi").getAsString());
                    gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    adapter = new EtalaseListAdapter(EtalaseActivity.this,response.body());
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(EtalaseActivity.this, "Load Gagal", Toast.LENGTH_SHORT).show();
                }
                pDialog.cancel();
            }

            @Override
            public void onFailure(Call<EtalaseRes> call, Throwable t) {

            }
        });
    }
}