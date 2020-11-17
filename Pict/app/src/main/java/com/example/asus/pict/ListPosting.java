package com.example.asus.pict;

import java.io.Serializable;

public class ListPosting implements Serializable {

    private String id;
    private String photo_name;
    private String caption;
    private String harga;
    private String nama_users;
    private String image;
    private String email;
    private String message;
    private String noHpProvider;
    private String nama_users2;

    public ListPosting(String id, String photo_name, String caption, String harga, String nama_users, String image, String email,String nama_users2) {
        this.id = id;
        this.photo_name = photo_name;
        this.caption = caption;
        this.harga = harga;
        this.nama_users = nama_users;
        this.image = image;
        this.email = email;
        this.nama_users2 = nama_users2;
    }


    public String getId() {
        return id;
    }
    public String getMessage(){
        return message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public String getCaption() {
        return caption;
    }
    public String getHarga(){
        return harga;
    }
    public void setHarga(String harga){this.harga = harga;}

    public void setCaption(String caption) {
        this.caption = caption;
    }


    public String getNama_users() {
        return nama_users;
    }

    public void setNama_users(String nama_users) {
        this.nama_users = nama_users;
    }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public String getNama_users2() {
        return nama_users2;
    }

    public void setNama_users2(String nama_users2) {
        this.nama_users2 = nama_users2;
    }


}
