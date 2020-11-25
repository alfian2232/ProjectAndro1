package com.example.asus.pict.Request;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class RegReq implements Serializable {
    String nama, nomer, email, user, alamat, password,toko,des;

    public RegReq(String nama, String nomer, String email, String user, String alamat, String password, String toko, String des) {
        this.nama = nama;
        this.nomer = nomer;
        this.email = email;
        this.user = user;
        this.alamat = alamat;
        this.password = password;
        this.toko = toko;
        this.des = des;
    }
    public RegReq() {
    }

    public String getToko() {
        return toko;
    }

    public void setToko(String toko) {
        this.toko = toko;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    protected RegReq(Parcel in) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
