package com.example.asus.pict.pembeli;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asus.pict.Petani.TambahProdukActivity;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.AddProdukRes;
import com.example.asus.pict.Request.PembeliRes;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembeliProfileActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_nama, et_nomer, et_alamat;
    Button btn_ubahprofil;
    LinearLayout to_password;
    int id;
    String nama, nomer, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli_profile);

        et_nama = findViewById(R.id.nama_pembeli);
        et_nomer = findViewById(R.id.nomer_pembeli);
        et_alamat = findViewById(R.id.alamat_pembeli);
        btn_ubahprofil = findViewById(R.id.btn_profil_pembeli);
        to_password = findViewById(R.id.btn_ubahpass);

        btn_ubahprofil.setOnClickListener(this);
        to_password.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        id = sharedPreferences.getInt("id",0);
        Log.i("adwqa",""+id);

        BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
        Call<PembeliRes> call = service.getPembeliProfil(id);
        call.enqueue(new Callback<PembeliRes>() {
            @Override
            public void onResponse(Call<PembeliRes> call, Response<PembeliRes> response) {
                if (!response.body().getError()){
                    et_nama.setText(response.body().getNama());
                    et_nomer.setText(response.body().getNomer());
                    et_alamat.setText(response.body().getAlamat());
                } else Toast.makeText(PembeliProfileActivity.this, "Gagal Ambil Data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PembeliRes> call, Throwable t) {
                Toast.makeText(PembeliProfileActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ubahpass:
                startActivity(new Intent(PembeliProfileActivity.this, PasswordPembeliActivity.class));
                break;
            case R.id.btn_profil_pembeli:
                final ProgressDialog pDialog = new ProgressDialog(PembeliProfileActivity.this);
                pDialog.setCancelable(false);
                pDialog.setMessage("Loading ...");
                pDialog.show();
                nama = et_nama.getText().toString();
                nomer = et_nomer.getText().toString();
                alamat = et_alamat.getText().toString();
                if (nama.isEmpty() || nomer.isEmpty() || alamat.isEmpty()){
                    Toast.makeText(PembeliProfileActivity.this, "Lengkapi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                    pDialog.cancel();
                } else {
                    BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
                    Call<AddProdukRes> call = service.updatePembeli(id,nama,nomer,alamat);
                    call.enqueue(new Callback<AddProdukRes>() {
                        @Override
                        public void onResponse(Call<AddProdukRes> call, Response<AddProdukRes> response) {
                            if (!response.body().getError()) {
                                Toast.makeText(PembeliProfileActivity.this, "" + response.body().getPesan(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(PembeliProfileActivity.this, "" + response.body().getPesan(), Toast.LENGTH_SHORT).show();
                            }
                            pDialog.cancel();
                        }

                        @Override
                        public void onFailure(Call<AddProdukRes> call, Throwable t) {
                            Toast.makeText(PembeliProfileActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            pDialog.cancel();
                        }
                    });
                }
        }
    }
}
