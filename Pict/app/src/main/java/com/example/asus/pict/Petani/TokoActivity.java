package com.example.asus.pict.Petani;

import android.app.Dialog;
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
import android.widget.Toast;

import com.example.asus.pict.R;
import com.example.asus.pict.Request.RegReq;
import com.example.asus.pict.Request.RegResponse;
import com.example.asus.pict.activity_update;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;
import com.example.asus.pict.apihelper.UtilsApi;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokoActivity extends AppCompatActivity {
    EditText et_namatoko, et_deskripsi;
    Button btn_daftar;
    String nama, nomer, email, user, alamat, password;
    BaseApiService mapiservice;
    JSONObject jsonObject = new JSONObject();
    SharedPreferences sharedPreferences;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toko);
        mapiservice = UtilsApi.getAPIService();
        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        initComponent();


        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            pDialog = new ProgressDialog(TokoActivity.this);
            pDialog.setCancelable(false);
            pDialog.setMessage("Loading ...");
            pDialog.show();
            final String nama_toko = et_namatoko.getText().toString();
            final String desc = et_deskripsi.getText().toString();
            if (nama_toko.isEmpty() || desc.isEmpty()){
                Toast.makeText(TokoActivity.this, "Lengkapi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                pDialog.cancel();
            } else {
                try {
                    jsonObject.put("nama_toko", nama_toko);
                    jsonObject.put("deskripsi", desc);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Bundle bundle = getIntent().getExtras();
                nama = bundle.getString("nama");
                nomer = bundle.getString("nomer");
                email = bundle.getString("email");
                user = bundle.getString("user");
                alamat = bundle.getString("alamat");
                password = bundle.getString("password");
                Log.i("abc", ""+alamat);
                Log.i("aaaa", ""+jsonObject.toString());
                BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
                Call<RegResponse> call = service.registerRequest(user,email,password, nama,nomer,alamat,jsonObject.toString());
                call.enqueue(new Callback<RegResponse>() {
                    @Override
                    public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                        if(!response.body().getError()){
                            Toast.makeText(TokoActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("id", response.body().getUid());
                            editor.putString("role", "petani");
                            editor.putBoolean("sudahLogin", true);
                            editor.putString("nama",nama);
                            editor.putString("nomer",nomer);
                            editor.putString("alamat",alamat);
                            editor.putString("nama_toko",nama_toko);
                            editor.putString("desc",desc);
                            editor.apply();
                            RegReq regReq = new RegReq(nama,nomer,email,user,alamat,password,nama_toko,desc);
                            Intent intent = new Intent(TokoActivity.this, HalamanUtamaActivity.class);
                            intent.putExtra("detailprofil",regReq);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(TokoActivity.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                        }
                        pDialog.cancel();
                    }

                    @Override
                    public void onFailure(Call<RegResponse> call, Throwable t) {
                        Toast.makeText(TokoActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.cancel();
                    }
                });
            }
            }
        });
    }

    private void initComponent(){
        et_namatoko = findViewById(R.id.tv_nama_toko);
        et_deskripsi = findViewById(R.id.tv_deskripsi);
        btn_daftar = findViewById(R.id.btn_daftar);
    }
}