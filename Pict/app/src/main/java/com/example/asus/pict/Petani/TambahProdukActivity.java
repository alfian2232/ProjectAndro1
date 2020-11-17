package com.example.asus.pict.Petani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.asus.pict.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TambahProdukActivity extends AppCompatActivity {

    Spinner spin_kategori;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);
        spin_kategori = findViewById(R.id.spin_kategori);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        List<String> categories = new ArrayList<String>();
        categories.add("Sayur Hijau");
        categories.add("Sayur Kuning");
        categories.add("Sayur Merah");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kategori.setAdapter(dataAdapter);

    }
}