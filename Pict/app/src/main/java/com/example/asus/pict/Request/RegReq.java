package com.example.asus.pict.Request;

import android.os.Parcel;
import android.os.Parcelable;

public class RegReq implements Parcelable {
    String nama, nomer, email, user, alamat, password;

    public RegReq(String nama, String nomer, String email, String user, String alamat, String password) {
        this.nama = nama;
        this.nomer = nomer;
        this.email = email;
        this.user = user;
        this.alamat = alamat;
        this.password = password;
    }

    protected RegReq(Parcel in) {
    }

    public static final Creator<RegReq> CREATOR = new Creator<RegReq>() {
        @Override
        public RegReq createFromParcel(Parcel in) {
            return new RegReq(in);
        }

        @Override
        public RegReq[] newArray(int size) {
            return new RegReq[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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

    public static Creator<RegReq> getCREATOR() {
        return CREATOR;
    }
}
