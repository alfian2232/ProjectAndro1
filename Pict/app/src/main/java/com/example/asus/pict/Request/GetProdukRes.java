package com.example.asus.pict.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class GetProdukRes {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_petani")
    @Expose
    private Integer idPetani;
    @SerializedName("produk")
    @Expose
    private JSONObject produk;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("img")
    @Expose
    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPetani() {
        return idPetani;
    }

    public void setIdPetani(Integer idPetani) {
        this.idPetani = idPetani;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public JSONObject getProduk() {
        return produk;
    }

    public void setProduk(JSONObject produk) {
        this.produk = produk;
    }
}
