package com.example.asus.pict;

public class ListProfilePosting {

    private String id;
    private String photo_name;
    private String caption;
    private String nama_users;
    private String image;
    private String nama_users2;

    public ListProfilePosting(String id, String photo_name, String caption, String nama_users, String image, String nama_users2) {
        this.id = id;
        this.photo_name = photo_name;
        this.caption = caption;
        this.nama_users = nama_users;
        this.image = image;
        this.nama_users2 = nama_users2;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

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
