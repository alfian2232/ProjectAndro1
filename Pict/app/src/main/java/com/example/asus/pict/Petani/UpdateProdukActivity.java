package com.example.asus.pict.Petani;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.AddProdukRes;
import com.example.asus.pict.activity_update;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProdukActivity extends AppCompatActivity {
    EditText spin_kategori,et_produk,et_desc,et_harga,et_berat,et_stok;
    Toolbar toolbar;
    Uri contentUri;
    Bitmap imageUri;
    ImageView iv_produk;
    LinearLayout btn_image;
    String nama_produk,desc,kategori;
    String image="b";
    float harga,berat;
    int stok;
    private File f = new File("b");
    Button btn_update;
    JSONObject jsonObject = new JSONObject();
    ProgressDialog pDialog;
    private byte[] byteArray;
    ByteArrayOutputStream byteArrayOutputStream;
    private static final int PICK_IMAGE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_produk);
        spin_kategori = findViewById(R.id.spin_kategori);
        toolbar = findViewById(R.id.toolbar);
        btn_image = findViewById(R.id.ll_image);
        iv_produk = findViewById(R.id.iv_produk);
        et_produk = findViewById(R.id.tv_nama);
        et_desc = findViewById(R.id.tv_deskripsi);
        et_harga = findViewById(R.id.tv_harga);
        et_berat = findViewById(R.id.tv_berat);
        et_stok = findViewById(R.id.tv_stok);
        btn_update = findViewById(R.id.btn_simpan);

        final int id = getIntent().getIntExtra("id",0);
        et_produk.setText(getIntent().getStringExtra("nama_produk"));
        Log.i("asfsadf",""+getIntent().getStringExtra("produk"));
        Glide.with(UpdateProdukActivity.this).load(getIntent().getStringExtra("image")).into(iv_produk);
        et_desc.setText(getIntent().getStringExtra("desc"));
        et_harga.setText(""+getIntent().getFloatExtra("harga",0));
        et_berat.setText(""+getIntent().getFloatExtra("harga",0));
        et_stok.setText(""+getIntent().getIntExtra("stok",0));
        spin_kategori.setText(getIntent().getStringExtra("kategori"));

        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalery();
            }

        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama_produk = et_produk.getText().toString();
                desc = et_desc.getText().toString();
                kategori = spin_kategori.getText().toString();
                harga = Float.parseFloat(et_harga.getText().toString());
                stok = Integer.parseInt(et_stok.getText().toString());
                berat = Float.parseFloat(et_berat.getText().toString());
                pDialog = new ProgressDialog(UpdateProdukActivity.this);
                pDialog.setCancelable(false);
                pDialog.setMessage("Loading ...");
                pDialog.show();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("nama_produk", nama_produk);
                    jsonObject.put("desc", desc);
                    jsonObject.put("harga", harga);
                    jsonObject.put("stok", stok);
                    jsonObject.put("berat", berat);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (nama_produk.isEmpty() || desc.isEmpty() || kategori.isEmpty() || harga == 0.0 || berat == 0.0 || stok == 0){
                    Toast.makeText(UpdateProdukActivity.this, "Lengkapi Data Semua", Toast.LENGTH_SHORT).show();
                    pDialog.cancel();
                }else{
                    BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
                    Call<AddProdukRes> call = service.updateProduk(id,jsonObject.toString(),kategori,f.getName(),image);
                    call.enqueue(new Callback<AddProdukRes>() {
                        @Override
                        public void onResponse(Call<AddProdukRes> call, Response<AddProdukRes> response) {
                            if(!response.body().getError()){
                                Toast.makeText(UpdateProdukActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UpdateProdukActivity.this,ProdukSayaActivity.class));
                            }else{
                                Toast.makeText(UpdateProdukActivity.this, "Update Gagal", Toast.LENGTH_SHORT).show();
                            }
                            pDialog.cancel();
                        }

                        @Override
                        public void onFailure(Call<AddProdukRes> call, Throwable t) {
                            Toast.makeText(UpdateProdukActivity.this, "Koneksi Gagal ", Toast.LENGTH_SHORT).show();
                            pDialog.cancel();
                        }
                    });
                }
            }
        });
    }

    private void openGalery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                if (data != null) {
                    contentUri = data.getData();
                    try {
                        imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(UpdateProdukActivity.this).getContentResolver(), contentUri);
                        String selectedPath = getPaths(contentUri);
                        iv_produk.setImageBitmap(imageUri);
                        f = new File(selectedPath);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        imageUri.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        image = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        Log.i("gambar", "" + image);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(UpdateProdukActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private String getPaths(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = UpdateProdukActivity.this.managedQuery(contentUri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
}