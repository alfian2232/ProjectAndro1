package com.example.asus.pict.Petani;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.pict.R;
import com.example.asus.pict.activity_posting;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TambahProdukActivity extends AppCompatActivity {

    EditText spin_kategori,et_produk,et_desc,et_harga,et_berat;
    Toolbar toolbar;
    LinearLayout btn_image;
    final int PICK_IMAGE = 100;
    Uri contentUri;
    Bitmap imageUri;
    ImageView iv_produk;
    TextView tv_poto;
    String nama_produk,desc,kategori,image;
    float harga,berat;
    Button btn_simpan;
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
        btn_simpan = findViewById(R.id.btn_simpan);

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

                if (nama_produk.isEmpty() || desc.isEmpty() || kategori.isEmpty() || harga == 0.0 || berat == 0.0 || image==null){
                    Toast.makeText(TambahProdukActivity.this, "Lengkapi Data Semua", Toast.LENGTH_SHORT).show();
                    pDialog.cancel();
                }else{

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if (data != null) {
                contentUri = data.getData();
                try {
                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(TambahProdukActivity.this).getContentResolver(), contentUri);
                    iv_produk.setImageBitmap(imageUri);
                    tv_poto.setVisibility(View.GONE);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    imageUri.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream .toByteArray();
                    image = Base64.encodeToString(byteArray, Base64.DEFAULT);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(TambahProdukActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
}