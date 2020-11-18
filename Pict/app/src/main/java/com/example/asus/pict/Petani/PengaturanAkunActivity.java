package com.example.asus.pict.Petani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.asus.pict.R;

public class PengaturanAkunActivity extends AppCompatActivity {

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
            }
        });


    }
}