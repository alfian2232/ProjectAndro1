package com.example.asus.pict.pembeli;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.pict.Petani.ActivityLogin;
import com.example.asus.pict.Petani.HalamanUtamaActivity;
import com.example.asus.pict.Petani.RegisterActivity;
import com.example.asus.pict.Petani.TambahProdukActivity;
import com.example.asus.pict.Petani.TokoActivity;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.RegResponse;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembeliRegisterActivity extends AppCompatActivity {
    Button btn_daftar;
    TextView idSignin;
    EditText et_nama, et_nomer, et_email, et_username, et_alamat, et_password;
    String nama, nomer, email, user, alamat, password;
    SharedPreferences sharedPreferences;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli_register);
        initComponent();
        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(PembeliRegisterActivity.this);
                pDialog.setCancelable(false);
                pDialog.setMessage("Loading ...");
                pDialog.show();
                nama=et_nama.getText().toString();
                nomer=et_nomer.getText().toString();
                email=et_email.getText().toString();
                user=et_username.getText().toString();
                alamat=et_alamat.getText().toString();
                password=et_password.getText().toString();
                if (nama.isEmpty() || nomer.isEmpty() ||email.isEmpty() ||user.isEmpty() ||alamat.isEmpty() ||password.isEmpty()){
                    Toast.makeText(PembeliRegisterActivity.this, "Lengkapi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
                    Call<RegResponse> call = service.regisReqPembeli(user,email,password, nama,nomer,alamat);
                    call.enqueue(new Callback<RegResponse>() {
                        @Override
                        public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                            if(!response.body().getError()){
                                Toast.makeText(PembeliRegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("id", response.body().getUid());
                                editor.putString("role", "petani");
                                editor.putBoolean("sudahLogin", true);
                                editor.apply();
                                startActivity(new Intent(PembeliRegisterActivity.this, PembeliMainActivity.class));
                            }else{
                                Toast.makeText(PembeliRegisterActivity.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                            }
                            pDialog.cancel();
                        }

                        @Override
                        public void onFailure(Call<RegResponse> call, Throwable t) {
                            Toast.makeText(PembeliRegisterActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            pDialog.cancel();
                        }
                    });
                }
            }
        });

        idSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PembeliRegisterActivity.this, ActivityLogin.class));
            }
        });
    }

    private void initComponent(){
        btn_daftar = findViewById(R.id.pembeli_btnreg);
        idSignin = findViewById(R.id.pembeli_txtsignin);
        et_nama = findViewById(R.id.pembeli_namareg);
        et_nomer = findViewById(R.id.pembeli_noreg);
        et_email = findViewById(R.id.pembeli_emailreg);
        et_username = findViewById(R.id.pembeli_userreg);
        et_alamat = findViewById(R.id.pembeli_alamat);
        et_password = findViewById(R.id.pembeli_passreg);
    }
}