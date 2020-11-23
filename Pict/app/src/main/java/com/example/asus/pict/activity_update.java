package com.example.asus.pict;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class activity_update extends AppCompatActivity {

    EditText etCaption,etHarga;
    ImageView iv_produk;
    ListPosting item;
    File f = new File("");
    FloatingActionButton btnUpload;
    ProgressDialog pDialog;
    SharedPreferences sharedPreferences;
    String id_users;
    Bitmap imageUri;
    private byte[] byteArray;
    ByteArrayOutputStream byteArrayOutputStream;
    private static final int PICK_IMAGE = 100;
    private String ConvertImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etCaption = findViewById(R.id.etCaption);
        etHarga = findViewById(R.id.etHarga);
        iv_produk = findViewById(R.id.id_image);
        btnUpload=findViewById(R.id.btnUpload);

        item = (ListPosting) getIntent().getSerializableExtra("itemproduk");
        Log.i("hsadsa","as"+new Gson().toJson(item));

        etCaption.setText(item.getCaption());
        etHarga.setText(""+item.getHarga());
//        Glide.with(activity_update.this).asBitmap()
//                .load("https://bogelardi.000webhostapp.com/api_kuliah/uploads/" + item.getPhoto_name())
//                .into(iv_produk);
        Glide.with(this)
                .asBitmap()
                .load("https://vegetarianmarket.000webhostapp.com/api_kuliah/uploads/" + item.getPhoto_name())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_produk.setImageBitmap(resource);
                        imageUri=resource;
                    }
                });
        iv_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalery();
            }
        });


        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        id_users = sharedPreferences.getString("id",null);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(activity_update.this);
                pDialog.setCancelable(false);
                pDialog.setMessage("Loading ...");
                pDialog.show();
                final String getJudul = etCaption.getText().toString().trim();
                final String getHarga = etHarga.getText().toString().trim();
                if(getJudul.isEmpty() || getHarga.isEmpty()){
                    Toast.makeText(activity_update.this, "Isi semua data terlebih dahulu", Toast.LENGTH_SHORT).show();
                    pDialog.cancel();
                }else{
                    if(imageUri!=null){
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        imageUri.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byteArray = byteArrayOutputStream.toByteArray();
                        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

                        final String URL = "https://vegetarianmarket.000webhostapp.com/api_kuliah/";
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

//                        BaseApiService service = retrofit.create(BaseApiService.class);
//                        Call<ResponseBody> call = service.updateProduk(Integer.parseInt(id_users),ConvertImage,f.getName()==""?item.getPhoto_name():f.getName(),getJudul,getHarga,Integer.parseInt(item.getId()));
//                        call.enqueue(new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                if(response.code() == 200){
//                                    Toast.makeText(activity_update.this, "Berhasil Dupdate", Toast.LENGTH_SHORT).show();
//                                    startActivityForResult(new Intent(activity_update.this,MainActivity.class),12);
//                                }else{
//                                    Toast.makeText(activity_update.this, "Gagal Diupdate", Toast.LENGTH_SHORT).show();
//                                }
//                                pDialog.cancel();
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                            }
//                        });
                    }else{
                        Toast.makeText(activity_update.this, "Masukkan Gambar", Toast.LENGTH_SHORT).show();
                    }
                    }
            }
        });
    }

    private  void openGalery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    private String getPaths(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity_update.this.managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if (data != null) {
                Uri contentUri = data.getData();

                try {
                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(activity_update.this).getContentResolver(), contentUri);
                    String selectedPath = getPaths(contentUri);
                    iv_produk.setImageBitmap(imageUri);
                    Log.i("gambar",""+imageUri);
                    f = new File(selectedPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(activity_update.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}