package com.example.asus.pict.Petani;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.asus.pict.GetStartedFirst;
import com.example.asus.pict.R;

public class PengaturanAkunActivity extends AppCompatActivity {
    Intent intent;
    CardView cv_profil,cv_kebijakan,cv_informasi;
    Button btn_keluar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_akun);
        cv_profil = findViewById(R.id.cv_profil);
        cv_kebijakan = findViewById(R.id.cv_kebijakan);
        cv_informasi = findViewById(R.id.cv_info);
        btn_keluar = findViewById(R.id.btn_keluar);

        cv_kebijakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PengaturanAkunActivity.this,KebijakanAppActivity.class));
            }
        });

        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SharedPreferences sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id", 0);
                editor.putString("role", null);
                editor.putBoolean("sudahLogin", false);
                editor.apply();
                startActivity(new Intent(PengaturanAkunActivity.this, GetStartedFirst.class));
                finish();
            }
        });

        cv_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PengaturanAkunActivity.this, ProfilActivity.class);
                startActivity(intent1);
            }
        });

        cv_informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PengaturanAkunActivity.this,InformasiActivity.class));
            }
        });


    }
}