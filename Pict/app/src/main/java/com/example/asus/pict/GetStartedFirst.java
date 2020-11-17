package com.example.asus.pict;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStartedFirst extends AppCompatActivity {
Button btn_masuk, btn_daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_first);

        btn_daftar = findViewById(R.id.btn_daftar);
        btn_masuk = findViewById(R.id.btn_masuk);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetStartedFirst.this,GetStarted.class));
            }
        });

        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetStartedFirst.this,GetStartedMasuk.class));
            }
        });
    }
}