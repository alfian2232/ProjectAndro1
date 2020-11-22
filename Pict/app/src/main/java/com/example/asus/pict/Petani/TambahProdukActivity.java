package com.example.asus.pict.Petani;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.pict.MainActivity;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.AddProdukRes;
import com.example.asus.pict.Request.RegResponse;
import com.example.asus.pict.Request.RequestHandler;
import com.example.asus.pict.activity_posting;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahProdukActivity extends AppCompatActivity {

    EditText spin_kategori,et_produk,et_desc,et_harga,et_berat,et_stok;
    Toolbar toolbar;
    LinearLayout btn_image;
    final int PICK_IMAGE = 100;
    Uri contentUri;
    Bitmap imageUri;
    ImageView iv_produk;
    TextView tv_poto;
    String nama_produk,desc,kategori,image;
    float harga,berat;
    int stok;
    int id_petani;
    private File f = new File("");
    Button btn_simpan;
    JSONObject jsonObject = new JSONObject();
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);
        spin_kategori = findViewById(R.id.spin_kategori);
        toolbar = findViewById(R.id.toolbar);
        btn_image = findViewById(R.id.ll_image);
        iv_produk = findViewById(R.id.iv_produk);
        tv_poto = findViewById(R.id.tv_poto);
        et_produk = findViewById(R.id.tv_nama);
        et_desc = findViewById(R.id.tv_deskripsi);
        et_harga = findViewById(R.id.tv_harga);
        et_berat = findViewById(R.id.tv_berat);
        et_stok = findViewById(R.id.tv_stok);
        btn_simpan = findViewById(R.id.btn_simpan);

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        id_petani = sharedPreferences.getInt("id", 0);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(TambahProdukActivity.this);
                pDialog.setCancelable(false);
                pDialog.setMessage("Loading ...");
                pDialog.show();
                nama_produk = et_produk.getText().toString();
                desc = et_desc.getText().toString();
                kategori = spin_kategori.getText().toString();
                harga = Float.parseFloat(et_harga.getText().toString());
                berat = Float.parseFloat(et_berat.getText().toString());
                stok = Integer.parseInt(et_stok.getText().toString());

                if (nama_produk.isEmpty() || desc.isEmpty() || kategori.isEmpty() || harga == 0.0 || berat == 0.0 || stok == 0
                        || image==null || f.getName().equals("")){
                    Toast.makeText(TambahProdukActivity.this, "Lengkapi Data Semua", Toast.LENGTH_SHORT).show();
                    pDialog.cancel();
                }else{
                    try {
                        jsonObject.put("nama_produk",nama_produk);
                        jsonObject.put("deskripsi", desc);
                        jsonObject.put("harga",harga);
                        jsonObject.put("berat",berat);
                        jsonObject.put("stok",stok);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
                    Call<AddProdukRes> call = service.addprodukRequest(id_petani, jsonObject.toString(), kategori, f.getName(), image);
                    call.enqueue(new Callback<AddProdukRes>() {
                        @Override
                        public void onResponse(Call<AddProdukRes> call, Response<AddProdukRes> response) {
                            if(!response.body().getError()){
                                Toast.makeText(TambahProdukActivity.this, "Berhasil Tambah Produk", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(TambahProdukActivity.this,ProdukSayaActivity.class));
                            }else{
                                Toast.makeText(TambahProdukActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                            pDialog.cancel();
                        }

                        @Override
                        public void onFailure(Call<AddProdukRes> call, Throwable t) {
                            Toast.makeText(TambahProdukActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            pDialog.cancel();
                        }
                    });
                }
            }
        });

    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = TambahProdukActivity.this.managedQuery(uri, projection, null, null, null);
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
//            if (data != null) {
//                contentUri = data.getData();
//
//                try {
//                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(TambahProdukActivity.this).getContentResolver(), contentUri);
//                    String selectedPath = getPath(contentUri);
//                    iv_produk.setImageBitmap(imageUri);
//                    iv_produk.setVisibility(View.VISIBLE);
//                    f = new File(selectedPath);
//                    imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
//                    byteArray = byteArrayOutputStream.toByteArray();
//                    ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Toast.makeText(TambahProdukActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
//                }
//
//            }
            if (data != null) {
                contentUri = data.getData();
                try {
                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(TambahProdukActivity.this).getContentResolver(), contentUri);
                    String selectedPath = getPath(contentUri);
                    iv_produk.setImageBitmap(imageUri);
                    tv_poto.setVisibility(View.GONE);
                    f = new File(selectedPath);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    imageUri.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream .toByteArray();
                    image = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    Log.i("asfdsa",""+f.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(TambahProdukActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}