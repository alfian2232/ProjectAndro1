package com.example.asus.pict.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProdukLimitRes {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private List<DataProdukLimitRes> data = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<DataProdukLimitRes> getData() {
        return data;
    }

    public void setData(List<DataProdukLimitRes> data) {
        this.data = data;
    }
}
