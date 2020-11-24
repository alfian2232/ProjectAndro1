package com.example.asus.pict.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PembeliRes {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nomer")
    @Expose
    private String nomer;
    @SerializedName("alamat")
    @Expose
    private String alamat;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
