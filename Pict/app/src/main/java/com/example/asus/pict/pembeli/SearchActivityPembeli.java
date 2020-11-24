package com.example.asus.pict.pembeli;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asus.pict.Adapter.ProdukLimitAdapter;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.DataProdukLimitRes;
import com.example.asus.pict.Request.ProdukLimitRes;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivityPembeli extends AppCompatActivity {
    ProgressDialog pDialog;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    ProdukLimitAdapter adapter;
    List<DataProdukLimitRes> ListProduk = new ArrayList<>();
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pembeli2);
        searchView = findViewById(R.id.searchview);
        recyclerView = findViewById(R.id.rv_allproduk);

        pDialog = new ProgressDialog(SearchActivityPembeli.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        pDialog.show();
        BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
        Call<ProdukLimitRes> call = service.getProdukLimit();
        call.enqueue(new Callback<ProdukLimitRes>() {
            @Override
            public void onResponse(Call<ProdukLimitRes> call, Response<ProdukLimitRes> response) {
                if (!response.body().getError()){
                    gridLayoutManager = new GridLayoutManager(SearchActivityPembeli.this, 2);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    adapter = new ProdukLimitAdapter(SearchActivityPembeli.this,response.body());
                    recyclerView.setAdapter(adapter);
                    ListProduk = response.body().getData();
                }else{
                    Toast.makeText(SearchActivityPembeli.this, "Load Gagal", Toast.LENGTH_SHORT).show();
                }
                pDialog.cancel();
            }

            @Override
            public void onFailure(Call<ProdukLimitRes> call, Throwable t) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String nextText) {
                nextText = nextText.toLowerCase();
                List<DataProdukLimitRes> searchResponse = new ArrayList<>();
                ProdukLimitRes ListSearch = new ProdukLimitRes();
                for(DataProdukLimitRes data : ListProduk){
                    JsonParser jsonParser = new JsonParser();
                    JsonObject hasil = (JsonObject) jsonParser.parse(data.getProduk());
                    String nama  = hasil.get("nama_produk").getAsString();
                    if(nama.contains(nextText)){
                        searchResponse.add(data);
                        ListSearch.setData(searchResponse);
                    }
                }
                recyclerView.setLayoutManager(gridLayoutManager);
                adapter = new ProdukLimitAdapter(SearchActivityPembeli.this,ListSearch);
                recyclerView.setAdapter(adapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
}