package com.example.asus.pict;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.pict.Petani.HalamanUtamaActivity;
import com.example.asus.pict.pembeli.PembeliMainActivity;

public class ActivitySplashScreen extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
                String role = sharedPreferences.getString("role","");
                Boolean login = sharedPreferences.getBoolean("sudahLogin", false);
                if (login){
                    if (role.equals("petani")){
                        Intent home = new Intent(ActivitySplashScreen.this, HalamanUtamaActivity.class);
                        startActivity(home);
                        finish();
                    } else {

                        Intent home = new Intent(ActivitySplashScreen.this, PembeliMainActivity.class);
                        startActivity(home);
                        finish();
                    }
                } else {
                    Intent home = new Intent(ActivitySplashScreen.this, GetStartedFirst.class);
                    startActivity(home);
                    finish();
                }
            }
        },2000);
    }
}
