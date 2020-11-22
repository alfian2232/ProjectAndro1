package com.example.asus.pict.Request;

public class ProdukReq {
    String nama_produk,desc;
    float harga,berat;
    int stok;

    public ProdukReq(String nama_produk, String desc, float harga, float berat, int stok) {
        this.nama_produk = nama_produk;
        this.desc = desc;
        this.harga = harga;
        this.berat = berat;
        this.stok = stok;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public float getBerat() {
        return berat;
    }

    public void setBerat(float berat) {
        this.berat = berat;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
