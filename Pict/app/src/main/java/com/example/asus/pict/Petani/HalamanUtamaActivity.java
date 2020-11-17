package com.example.asus.pict.Petani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.asus.pict.R;

public class HalamanUtamaActivity extends AppCompatActivity {

    CardView cv_produk,cv_tambah,cv_pengaturan_akun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);
        cv_produk = findViewById(R.id.cv_produk);
        cv_tambah = findViewById(R.id.cv_tambah);
        cv_pengaturan_akun = findViewById(R.id.cv_pengaturan_akun);

        cv_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HalamanUtamaActivity.this,ProdukSayaActivity.class));
            }
        });

        

    }
}