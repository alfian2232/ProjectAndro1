package com.example.asus.pict;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class GetStarted extends AppCompatActivity {
    Button btn_petani ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        btn_petani= findViewById(R.id.btn_petani);


    }
}