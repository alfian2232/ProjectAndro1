package com.example.asus.pict.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.pict.AdapterUserList;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.GetProdukRes;

import org.json.JSONException;

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
    public ProdukPetaniAdapter.ProdukHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_item_produk_saya, parent, false);
        return new ProdukHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukPetaniAdapter.ProdukHolder holder, int position) {
        try {
            holder.stok.setText("Stok : "+ListProduk.get(position).getProduk().getString("stok"));
            holder.nama_produk.setText(ListProduk.get(position).getProduk().getString("nama_produk"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 0;
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
