package com.example.asus.pict;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.pict.Petani.ActivityLogin;

public class GetStartedMasuk extends AppCompatActivity {

    Button btn_petani;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_masuk);
        btn_petani = findViewById(R.id.btn_petani);

        btn_petani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetStartedMasuk.this, ActivityLogin.class));
            }
        });
    }
}