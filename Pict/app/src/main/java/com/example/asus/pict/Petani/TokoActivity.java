package com.example.asus.pict.Petani;

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
    Bundle bundle;
    String nama, nomer, email, user, alamat, password;
    BaseApiService mapiservice;
    JSONObject jsonObject = new JSONObject();
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toko);
        mapiservice = UtilsApi.getAPIService();
        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        initComponent();

        try {
            jsonObject.put("nama_toko", et_namatoko.getText());
            jsonObject.put("deskripsi", et_deskripsi.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_namatoko.getText().equals("") || et_deskripsi.getText().equals("")){
                    Toast.makeText(TokoActivity.this, "Lengkapi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    RegReq regReq = getIntent().getExtras().getParcelable("sds");
                    Log.i("abc", ""+regReq.getNama());
                    Log.i("aaaa", ""+jsonObject.toString());
                    BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
                    Call<RegResponse> call = service.registerRequest(user,email,password, "namas",nomer,alamat,"jsonObject.toString()");
                    call.enqueue(new Callback<RegResponse>() {
                        @Override
                        public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                            if(!response.body().getError()){
                                Toast.makeText(TokoActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
//                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                editor.putString("user", user);
//                                editor.putString("role", "petani");
//                                editor.putBoolean("sudahLogin", true);
//                                editor.apply();
                            }else{
                                Toast.makeText(TokoActivity.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegResponse> call, Throwable t) {
                            Toast.makeText(TokoActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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