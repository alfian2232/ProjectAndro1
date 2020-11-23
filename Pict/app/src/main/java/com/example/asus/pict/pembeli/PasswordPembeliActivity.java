package com.example.asus.pict.pembeli;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.pict.R;
import com.example.asus.pict.Request.AddProdukRes;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordPembeliActivity extends AppCompatActivity {
    EditText et_passlama, et_passbaru, et_konfir;
    Button btn_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_pembeli);

        et_passlama = findViewById(R.id.pass_lama);
        et_passbaru = findViewById(R.id.pass_baru);
        et_konfir = findViewById(R.id.kon_passbaru);
        btn_password = findViewById(R.id.btn_password);

        SharedPreferences sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        final int id = sharedPreferences.getInt("id",0);

        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passlama = et_passlama.getText().toString();
                String passbaru = et_passbaru.getText().toString();
                String konf = et_konfir.getText().toString();
                if (passlama.isEmpty() || passbaru.isEmpty() || konf.isEmpty()){
                    Toast.makeText(PasswordPembeliActivity.this, "Lengkapi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    if (passbaru != konf){
                        Toast.makeText(PasswordPembeliActivity.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                        et_konfir.requestFocus();
                    } else {
                        BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
                        Call<AddProdukRes> call = service.updatePassword(id, passlama, passbaru);
                        call.enqueue(new Callback<AddProdukRes>() {
                            @Override
                            public void onResponse(Call<AddProdukRes> call, Response<AddProdukRes> response) {
                                if (!response.body().getError()){
                                    Toast.makeText(PasswordPembeliActivity.this, ""+response.body().getPesan(), Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(PasswordPembeliActivity.this, ""+response.body().getPesan(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<AddProdukRes> call, Throwable t) {
                                Toast.makeText(PasswordPembeliActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });

    }
}