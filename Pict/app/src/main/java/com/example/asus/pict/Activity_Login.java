package com.example.asus.pict;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Login extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    TextView btnRegister;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    TextView idSign;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id",null);
        String nama = sharedPreferences.getString("nama", null);
        String email = sharedPreferences.getString("email", null);
        String gambar = sharedPreferences.getString("gambar", null);
        boolean sudahLogin = sharedPreferences.getBoolean("sudahLogin",false);
        if (sudahLogin){
            Intent i = new Intent(Activity_Login.this, MainActivity.class);
            startActivity(i);
            finish();

        }

        initComponents();

        idSign = (TextView) findViewById(R.id.idSignup);
        idSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Login.this, ActivityRegister.class);
                startActivity(i);
                finish();
            }
        });



//        buttonLogin = (Button) findViewById(R.id.idButtonLogin);
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i= new Intent(Activity_Login.this, MainActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
    }

    private void initComponents() {
        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.idButtonLogin);
        btnRegister = (TextView) findViewById(R.id.idSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ActivityRegister.class));
            }
        });
    }


        private void requestLogin(){
            mApiService.loginRequest(etUsername.getText().toString(), etPassword.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
                                loading.dismiss();
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getString("error").equals("false")){
                                        // Jika login berhasil maka data nama yang ada di response API
                                        // akan diparsing ke activity selanjutnya.
                                        Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                        String id = jsonRESULTS.getString("uid");
                                        Log.d("debug","OnResponse"+id);


                                        String nama = jsonRESULTS.getJSONObject("user").getString("username");
                                        String email = jsonRESULTS.getJSONObject("user").getString("email");
                                        String gambar = jsonRESULTS.getJSONObject("user").getString("image");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();


                                        editor.putString("id", id);
                                        editor.putString("nama",nama);
                                        editor.putString("email",email);
                                        editor.putString("gambar",gambar);
                                        editor.putBoolean("sudahLogin", true);
                                        editor.apply();

                                        Intent intent = new Intent(Activity_Login.this, MainActivity.class);
                                        intent.putExtra("result_nama", nama);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // Jika login gagal
                                        String error_message = jsonRESULTS.getString("error_msg");
                                        Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("debug","OnResponse"+ e.getMessage());
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    Log.e("debug","OnResponse"+ e.getMessage());
                                    e.printStackTrace();
                                }
                            } else {
                                loading.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR > " + t.toString());
                            loading.dismiss();

                        }

                    });
        }

    }

