package com.example.asus.pict.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.pict.AdapterUserList;
import com.example.asus.pict.Petani.UpdateProdukActivity;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.GetProdukRes;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProdukPetaniAdapter extends RecyclerView.Adapter<ProdukPetaniAdapter.ProdukHolder>{
    Context mCtx;
    List<GetProdukRes> ListProduk;

    public ProdukPetaniAdapter(Context mCtx, List<GetProdukRes> listProduk) {
        this.mCtx = mCtx;
        ListProduk = listProduk;
    }

    @NonNull
    @Override
    public ProdukHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_item_produk_saya, parent, false);
        return new ProdukHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukHolder holder, int position) {
        JsonParser parser = new JsonParser();
        final JsonObject jsonObject = (JsonObject) parser.parse(ListProduk.get(position).getProduk());
        final String nama_produk = jsonObject.get("nama_produk").getAsString();
        final int stok = jsonObject.get("stok").getAsInt();
        final float berat = jsonObject.get("berat").getAsFloat();
        final int harga = jsonObject.get("harga").getAsInt();
        final String deskripsi = jsonObject.get("desc").getAsString();
        final String kategori = ListProduk.get(position).getKategori();
        final String image = ListProduk.get(position).getImg();
        final int id = ListProduk.get(position).getId();
        holder.nama_produk.setText(nama_produk);
        holder.stok.setText("Stok : "+stok);
        holder.kategori.setText("Kategori : "+ListProduk.get(position).getKategori());
        Glide.with(mCtx).load(ListProduk.get(position).getImg()).into(holder.iv_produk);
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, UpdateProdukActivity.class);
                intent.putExtra("nama_produk",nama_produk);
                intent.putExtra("stok",stok);
                intent.putExtra("kategori",kategori);
                intent.putExtra("id",id);
                intent.putExtra("berat",berat);
                intent.putExtra("harga",harga);
                intent.putExtra("desc",deskripsi);
                intent.putExtra("image",image);
                mCtx.startActivity(intent);
                ((Activity)mCtx).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListProduk.size();
    }

    public class ProdukHolder extends RecyclerView.ViewHolder {
        ImageView iv_produk;
        TextView nama_produk,stok,kategori;
        Button btn_edit;

        public ProdukHolder(View itemView) {
            super(itemView);
            iv_produk = itemView.findViewById(R.id.iv_produk);
            nama_produk = itemView.findViewById(R.id.tv_produk);
            stok = itemView.findViewById(R.id.tv_stock);
            kategori = itemView.findViewById(R.id.tv_kategori);
            btn_edit = itemView.findViewById(R.id.btn_ubah);
        }
    }
}
