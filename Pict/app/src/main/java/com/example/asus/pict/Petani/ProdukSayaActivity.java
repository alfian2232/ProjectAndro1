package com.example.asus.pict.Petani;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.asus.pict.Adapter.ProdukPetaniAdapter;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.GetProdukRes;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukSayaActivity extends AppCompatActivity {
    ProdukPetaniAdapter produkPetaniAdapter;
    List<GetProdukRes> ListProduk = new ArrayList<>();
    Toolbar toolbar;
    RecyclerView rv_produk_saya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_saya);

        toolbar = findViewById(R.id.toolbar);
        rv_produk_saya = findViewById(R.id.rv_produk_saya);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadData();


    }
    private void loadData() {
        final ProgressDialog pDialog = new ProgressDialog(ProdukSayaActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Memuat...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id",0);
        Map<String, String> query = new HashMap<>();
        query.put("id_petani", String.valueOf(id));

        BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
        Call<List<GetProdukRes>> call = service.getAllDataProdukByPetani(id);
        call.enqueue(new Callback<List<GetProdukRes>>() {
            @Override
            public void onResponse(Call<List<GetProdukRes>> call, Response<List<GetProdukRes>> response) {

                if (response.isSuccessful()) {
                    ListProduk = response.body();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProdukSayaActivity.this);
                    rv_produk_saya.setLayoutManager(layoutManager);
                    produkPetaniAdapter = new ProdukPetaniAdapter(ProdukSayaActivity.this, ListProduk);
                    rv_produk_saya.setAdapter(produkPetaniAdapter);
                } else {
                    Toast.makeText(ProdukSayaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
                pDialog.cancel();
            }

            @Override
            public void onFailure(Call<List<GetProdukRes>> call, Throwable t) {
                Toast.makeText(ProdukSayaActivity.this, "asem", Toast.LENGTH_SHORT).show();
                pDialog.cancel();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
