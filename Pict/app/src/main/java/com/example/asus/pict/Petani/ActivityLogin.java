package com.example.asus.pict.Petani;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.pict.R;
import com.example.asus.pict.Request.RegResponse;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {
    private String username,pass;
    Button idButtonLogin;
    EditText et_username,et_nama_toko,et_pass;
    SharedPreferences sharedPreferences;
    ProgressDialog pDialog;
    TextView idSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        idButtonLogin = findViewById(R.id.idButtonLogin);
        et_username = findViewById(R.id.et_userpetani);
        et_pass = findViewById(R.id.et_passpetani);
        idSignup = findViewById(R.id.idSignup);

        idSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        idButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(ActivityLogin.this);
                pDialog.setCancelable(false);
                pDialog.setMessage("Loading ...");
                pDialog.show();
                username = et_username.getText().toString();
                pass = et_pass.getText().toString();
                if(username.isEmpty() || pass.isEmpty()){
                    Toast.makeText(ActivityLogin.this, "Data Harus Lengkap!", Toast.LENGTH_SHORT).show();
                    pDialog.cancel();
                }else{
                    BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
                    Call<RegResponse> call = service.loginRequest(username,pass);
                    call.enqueue(new Callback<RegResponse>() {
                        @Override
                        public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                            if(!response.body().getError()){
                                sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("id", response.body().getUid());
                                editor.putString("role", "petani");
                                editor.putBoolean("sudahLogin", true);
                                editor.apply();
                                startActivity(new Intent(ActivityLogin.this,HalamanUtamaActivity.class));
                                Toast.makeText(ActivityLogin.this, "Login Succes", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ActivityLogin.this, "Login gagal", Toast.LENGTH_SHORT).show();
                            }
                            pDialog.cancel();
                        }

                        @Override
                        public void onFailure(Call<RegResponse> call, Throwable t) {
                            Toast.makeText(ActivityLogin.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            pDialog.cancel();
                        }
                    });
                }
            }
        });
    }
}