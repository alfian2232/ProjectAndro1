package com.example.asus.pict;

public class ListUser {

    private String id;
    private String username;
    private String email;
    private String image;
    private String jumlahlike;

    public ListUser(String id, String username, String email, String image, String jumlahlike) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.image = image;
        this.jumlahlike = jumlahlike;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getJumlahlike() {
        return jumlahlike;
    }

    public void setJumlahlike(String jumlahlike) {
        this.jumlahlike = jumlahlike;
    }
}
