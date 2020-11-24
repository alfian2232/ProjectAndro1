package com.example.asus.pict.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EtalaseRes {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("toko")
    @Expose
    private String toko;
    @SerializedName("nomer")
    @Expose
    private String nomer;
    @SerializedName("data")
    @Expose
    private List<DataEtalaseRes> data = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getToko() {
        return toko;
    }

    public void setToko(String toko) {
        this.toko = toko;
    }

    public List<DataEtalaseRes> getData() {
        return data;
    }

    public void setData(List<DataEtalaseRes> data) {
        this.data = data;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }
}
