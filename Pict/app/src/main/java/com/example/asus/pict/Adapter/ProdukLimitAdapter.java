package com.example.asus.pict.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.ProdukLimitRes;
import com.example.asus.pict.pembeli.DetailProdukActivity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ProdukLimitAdapter extends RecyclerView.Adapter<ProdukLimitAdapter.ProdukViewHolder> {
    String produk, nama, kategori, foto, nomer;
    int id, id_petani;
    Context context;
    ProdukLimitRes produkLimitRes;
    public ProdukLimitAdapter(Context context, ProdukLimitRes produkLimitRes) {
        this.context = context;
        this.produkLimitRes = produkLimitRes;
    }

    @NonNull
    @Override
    public ProdukLimitAdapter.ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_for_profile, parent, false);
        return new ProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProdukLimitAdapter.ProdukViewHolder holder, int position) {
        produk = produkLimitRes.getData().get(position).getProduk();
        JsonParser jsonParser = new JsonParser();
        JsonObject hasil = (JsonObject) jsonParser.parse(produk);

        holder.tv_nama.setText(hasil.get("nama_produk").getAsString());
        holder.tv_harga.setText("Rp "+hasil.get("harga").getAsString());
        Glide.with(context)
                .load(produkLimitRes.getData().get(position).getImg())
                .into(holder.iv_foto);
        holder.iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = produkLimitRes.getData().get(holder.getAdapterPosition()).getProduk();
                id = produkLimitRes.getData().get(holder.getAdapterPosition()).getId();
                id_petani = produkLimitRes.getData().get(holder.getAdapterPosition()).getIdPetani();
                nama = produkLimitRes.getData().get(holder.getAdapterPosition()).getNama();
                kategori = produkLimitRes.getData().get(holder.getAdapterPosition()).getKategori();
                foto = produkLimitRes.getData().get(holder.getAdapterPosition()).getImg();
                nomer = produkLimitRes.getData().get(holder.getAdapterPosition()).getNomer();

                Intent intent = new Intent(context, DetailProdukActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("data", data);
                bundle.putInt("id", id);
                bundle.putInt("id_petani", id_petani);
                bundle.putString("nama", nama);
                bundle.putString("kategori", kategori);
                bundle.putString("foto", foto);
                bundle.putString("nomer", nomer);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produkLimitRes.getData().size();
    }

    public class ProdukViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_foto;
        TextView tv_nama, tv_harga;
        CardView cardView;
        public ProdukViewHolder(View itemView) {
            super(itemView);
            iv_foto = itemView.findViewById(R.id.img_postingan_user);
            tv_nama = itemView.findViewById(R.id.nama_pro);
            tv_harga = itemView.findViewById(R.id.hargaproduk);
            cardView = itemView.findViewById(R.id.cv_produk);
        }
    }
}
