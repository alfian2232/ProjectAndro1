package com.example.asus.pict.Adapter;

import android.content.Context;
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
        JsonObject jsonObject = (JsonObject) parser.parse(ListProduk.get(position).getProduk());
        String nama_produk = jsonObject.get("nama_produk").getAsString();
        String stok = jsonObject.get("stok").toString();
        holder.nama_produk.setText(nama_produk);
        holder.stok.setText("Stok : "+stok);
        holder.kategori.setText("Kategori : "+ListProduk.get(position).getKategori());
        Glide.with(mCtx).load(ListProduk.get(position).getImg()).into(holder.iv_produk);
    }

    @Override
    public int getItemCount() {
        return ListProduk.size();
    }

    public class ProdukHolder extends RecyclerView.ViewHolder {
        ImageView iv_produk;
        TextView nama_produk,stok,kategori;
        Button btn_hapus,btn_edit;

        public ProdukHolder(View itemView) {
            super(itemView);
            iv_produk = itemView.findViewById(R.id.iv_produk);
            nama_produk = itemView.findViewById(R.id.tv_produk);
            stok = itemView.findViewById(R.id.tv_stock);
            kategori = itemView.findViewById(R.id.tv_kategori);
            btn_edit = itemView.findViewById(R.id.btn_ubah);
            btn_hapus = itemView.findViewById(R.id.btn_hapus);
        }
    }
}
