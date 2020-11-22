package com.example.asus.pict.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("uid")
    @Expose
    private Integer uid;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
