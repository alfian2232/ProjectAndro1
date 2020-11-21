package com.example.asus.pict;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRegister extends AppCompatActivity {

    EditText etemail, etusername, etpassword;
    Button btnRegister;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mapiservice;
    TextView idSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext = this;
            mapiservice = UtilsApi.getAPIService();
            initComponents();
        idSign = (TextView) findViewById(R.id.idSignin);
        idSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityRegister.this, Activity_Login.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void initComponents() {
        etusername = (EditText)findViewById(R.id.et_username_register);
        etemail = (EditText)findViewById(R.id.et_email_register);
        etpassword = (EditText)findViewById(R.id.et_password_register);
        btnRegister = (Button) findViewById(R.id.btnregister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu....", true, false);
//                requestRegister();
            }

//            private void requestRegister() {
//                mapiservice.registerRequest(etusername.getText().toString(),
//                        etemail.getText().toString(),etpassword.getText().toString()).enqueue(
//                        new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                if(response.isSuccessful()){
//                                    Log.i("debug", "onResponse: BERHASIL");
//                                    loading.dismiss();
//                                    try {
//                                        JSONObject jsonObject = new JSONObject(response.body().string());
//                                        if (jsonObject.getString("error").equals("false")){
//                                            Toast.makeText(mContext, "Register Success", Toast.LENGTH_SHORT).show();
//                                            startActivity(new Intent(mContext, Activity_Login.class));
//                                        }else {
//                                            String error_message = jsonObject.getString("error_msg");
//                                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
//                                        }
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                Log.e("debug", "onFailure: ERROR >" + t.getMessage());
////                                Toast.makeText(mContext,"koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(mContext, "Register Success", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(mContext, Activity_Login.class));
//                            }
//                        }
//                );
//            }
        });
    }
}
