package com.example.asus.pict.Petani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.pict.R;

public class RegisterActivity extends AppCompatActivity {
    Button btn_daftar;
    TextView idSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        btn_daftar = findViewById(R.id.btn_daftar);
        idSignin = findViewById(R.id.idSignin);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,HalamanUtamaActivity.class));
            }
        });

        idSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,ActivityLogin.class));
            }
        });
    }
}