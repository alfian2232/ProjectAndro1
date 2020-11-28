package com.example.asus.pict.Petani;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.asus.pict.R;
import com.example.asus.pict.Request.RegReq;
import com.example.asus.pict.Request.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ProfilActivity extends AppCompatActivity {
    TextView tv_nama,tv_username,tv_email,tv_alamat,tv_nomer,tv_nama_toko;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        tv_nama = findViewById(R.id.tv_nama);
        tv_alamat = findViewById(R.id.tv_alamat);
        tv_nomer = findViewById(R.id.tv_nomer);
        tv_nama_toko = findViewById(R.id.tv_namatoko);

        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);

        User user = (User) getIntent().getSerializableExtra("user");
        String nama = sharedPreferences.getString("nama","");
        String nomer = sharedPreferences.getString("nomer", "");
        String alamat = sharedPreferences.getString("alamat", "");
        String nama_toko = sharedPreferences.getString("nama_toko", "");

        tv_nama.setText(nama);
        tv_alamat.setText(alamat);
        tv_nomer.setText(nomer);
        tv_nama_toko.setText(nama_toko);
    }
}