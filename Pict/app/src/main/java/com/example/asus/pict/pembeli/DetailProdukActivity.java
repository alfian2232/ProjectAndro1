package com.example.asus.pict.pembeli;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.pict.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DetailProdukActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_namaproduk, tv_harga, tv_toko, tv_desc;
    ImageView iv_foto;
    Button btn_beli;
    String data, nama_produk, harga, berat, desc, stok, nomer,
    nama, kategori, img;
    int id, id_petani;
    LinearLayout toko;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        tv_namaproduk = findViewById(R.id.nama_produk);
        tv_harga = findViewById(R.id.hrg_produk);
        tv_toko = findViewById(R.id.tv_nama_toko);
        tv_desc = findViewById(R.id.desk_produk);
        iv_foto = findViewById(R.id.img_produk);
        btn_beli = findViewById(R.id.btn_beli);
        toko = findViewById(R.id.toko);
        Bundle bundle = getIntent().getExtras();
        data = bundle.getString("data");
        id = bundle.getInt("id");
        id_petani = bundle.getInt("id_petani");
        nama = bundle.getString("nama");
        kategori = bundle.getString("kategori");
        img = bundle.getString("foto");
        nomer = bundle.getString("nomer");
        Log.i("aaa", "onCreate: "+data);
        Log.i("aab", "onCreate: "+id);
        JsonParser jsonParser = new JsonParser();
        JsonObject hasil = (JsonObject) jsonParser.parse(data);
        JsonObject namatoko = (JsonObject) jsonParser.parse(nama);

        nama_produk = hasil.get("nama_produk").getAsString();
        harga = hasil.get("harga").getAsString();
        berat = hasil.get("berat").getAsString();
        desc = hasil.get("desc").getAsString();
        stok = hasil.get("stok").getAsString();
        tv_namaproduk.setText(nama_produk);
        tv_harga.setText("Rp. "+harga);
        tv_toko.setText(namatoko.get("nama_toko").getAsString());
        tv_desc.setText("berat : "+berat+" gram\nStok : "+stok+"\n"+desc);
        Glide.with(DetailProdukActivity.this)
                .load(img).apply(new RequestOptions())
                .into(iv_foto);

        btn_beli.setOnClickListener(this);
        toko.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_beli:
                String url = "https://api.whatsapp.com/send?phone="+ nomer.replaceFirst("^0+(?!$)","+62")+"&text="
                        + "Hai Saya Mau Membeli%20:%20" ;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.toko:
                Intent intent = new Intent(DetailProdukActivity.this, EtalaseActivity.class);
                intent.putExtra("id",id_petani);
                startActivity(intent);
                break;
        }
    }
}