package com.example.asus.pict;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.pict.Petani.ActivityLogin;
import com.example.asus.pict.pembeli.PembeliLoginActivity;
import com.example.asus.pict.pembeli.PembeliRegisterActivity;

public class GetStartedMasuk extends AppCompatActivity {

    Button btn_petani, btn_pembeli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_masuk);
        btn_petani = findViewById(R.id.btn_petani);
        btn_pembeli = findViewById(R.id.btn_pembeli);

        btn_petani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetStartedMasuk.this, ActivityLogin.class));
            }
        });
        btn_pembeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetStartedMasuk.this, PembeliLoginActivity.class));
            }
        });
    }
}