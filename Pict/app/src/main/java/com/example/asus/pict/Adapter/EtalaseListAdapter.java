package com.example.asus.pict.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.pict.Activity_posting_user;
import com.example.asus.pict.ListUserPosting;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.DataEtalaseRes;
import com.example.asus.pict.Request.EtalaseRes;
import com.example.asus.pict.pembeli.DetailProdukActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class EtalaseListAdapter extends RecyclerView.Adapter<EtalaseListAdapter.UserPostingViewHolder> {

    private Activity mCtx;
    private EtalaseRes userPostingList;
    String nama, kategori, foto, nomer;
    int id, id_petani;

    public EtalaseListAdapter(Activity mCtx, EtalaseRes userPosting) {
        this.mCtx = mCtx;
        this.userPostingList = userPosting;
    }

    @NonNull
    @Override
    public EtalaseListAdapter.UserPostingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_list_for_profile, parent, false);
        return new UserPostingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EtalaseListAdapter.UserPostingViewHolder holder, int position) {
        JsonParser parser = new JsonParser();
        JsonObject hasil = (JsonObject) parser.parse(userPostingList.getData().get(position).getProduk());

        Glide.with(mCtx)
                .load(userPostingList.getData().get(position).getImg())
                .into(holder.imageView);

        holder.captionView.setText(hasil.get("nama_produk").getAsString());
        holder.tv_harga.setText("Rp. "+hasil.get("harga").getAsFloat());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = userPostingList.getData().get(holder.getAdapterPosition()).getProduk();
                id = userPostingList.getData().get(holder.getAdapterPosition()).getId();
                id_petani = userPostingList.getData().get(holder.getAdapterPosition()).getIdPetani();
                nama = userPostingList.getToko();
                kategori = userPostingList.getData().get(holder.getAdapterPosition()).getKategori();
                foto = userPostingList.getData().get(holder.getAdapterPosition()).getImg();
                nomer = userPostingList.getNomer();

                Intent intent = new Intent(mCtx, DetailProdukActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("data", data);
                bundle.putInt("id", id);
                bundle.putInt("id_petani", id_petani);
                bundle.putString("nama", nama);
                bundle.putString("kategori", kategori);
                bundle.putString("foto", foto);
                bundle.putString("nomer", nomer);
                intent.putExtras(bundle);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userPostingList.getData().size();
    }


    public class UserPostingViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        View view;
        TextView captionView, tv_harga;
        public UserPostingViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            imageView = itemView.findViewById(R.id.img_postingan_user);
            cardView = itemView.findViewById(R.id.cv_produk);
            captionView = itemView.findViewById(R.id.nama_pro);
            tv_harga = itemView.findViewById(R.id.hargaproduk);
        }
    }
}
