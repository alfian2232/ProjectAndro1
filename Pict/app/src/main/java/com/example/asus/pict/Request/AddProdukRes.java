package com.example.asus.pict.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddProdukRes {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("pesan")
    @Expose
    private String pesan;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
