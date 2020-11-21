package com.example.asus.pict.Petani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.pict.R;

public class RegisterActivity extends AppCompatActivity {
    Button btn_daftar;
    TextView idSignin;
    EditText et_nama, et_nomer, et_email, et_username, et_alamat, et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        initComponent();
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_password.getText().equals("") || et_alamat.getText().equals("") || et_username.getText().equals("") ||
                        et_email.getText().equals("") || et_nomer.getText().equals("") || et_nama.getText().equals("")){
                    Toast.makeText(RegisterActivity.this, "Lengkapi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(RegisterActivity.this,TokoActivity.class);
                    intent.putExtra("nama", et_nama.getText());
                    intent.putExtra("nomer", et_nomer.getText());
                    intent.putExtra("email", et_email.getText());
                    intent.putExtra("user", et_username.getText());
                    intent.putExtra("alamat", et_alamat.getText());
                    intent.putExtra("password", et_password.getText());
                    startActivity(intent);
                }
            }
        });

        idSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,ActivityLogin.class));
            }
        });
    }

    private void initComponent(){
        btn_daftar = findViewById(R.id.btn_daftar);
        idSignin = findViewById(R.id.idSignin);
        et_nama = findViewById(R.id.tv_nama);
        et_nomer = findViewById(R.id.tv_no);
        et_email = findViewById(R.id.tv_email);
        et_username = findViewById(R.id.tv_username);
        et_alamat = findViewById(R.id.tv_alamat);
        et_password = findViewById(R.id.password);
    }
}