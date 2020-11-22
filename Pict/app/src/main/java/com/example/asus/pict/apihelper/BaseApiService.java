package com.example.asus.pict.apihelper;

import com.example.asus.pict.ListPosting;
import com.example.asus.pict.Request.RegResponse;
import com.example.asus.pict.Request.SearchResponse;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface BaseApiService {
    // Fungsi ini untuk memanggil API http://192.168.43.102/pict/login.php
    @FormUrlEncoded
    @POST("login.php")
    Call<RegResponse> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://192.168.43.102/pict/register.php
    @FormUrlEncoded
    @POST("register.php")
    Call<RegResponse> registerRequest(@Field("username") String username,
                                      @Field("email") String email,
                                      @Field("password") String password,
                                      @Field("nama") String nama,
                                      @Field("nomer") String nomer,
                                      @Field("alamat") String alamat,
                                      @Field("toko") String toko);

    @POST("addproduk.php")
    Call<RegResponse> addprodukRequest(@Field("id_petani") int id_petani,
                                      @Field("produk") String produk,
                                      @Field("kategori") String kategori,
                                      @Field("img") String img,
                                      @Field("foto_informasi") String foto_informasi);

    // Fungsi ini untuk memanggil API http://192.168.43.102/pict/add_favorite.php
    @FormUrlEncoded
    @POST("add_favorite.php")
    Call<ResponseBody> addFavoriteRequest(@Field("id_posting") String idPosting,
                                          @Field("id_user") String idUser);

    // Fungsi ini untuk memanggil API http://192.168.43.102/pict/remove_favorite.php
    @FormUrlEncoded
    @POST("remove_favorite.php")
    Call<ResponseBody> removeFavoriteRequest(@Field("id_posting") String idPosting,
                                             @Field("id_user") String idUser);
    @FormUrlEncoded
    @POST("delete.php")
    Call<ListPosting> hapus(@Field("id") int id);

    @GET("search.php")
    Call<List<SearchResponse>>getAllDataSearch();

    @GET("list_posting.php")
    Call<List<ListPosting>>getAllDataProduk();

    @FormUrlEncoded
    @POST("update_informasi.php")
    Call<ResponseBody> updateProduk(@Field("id_users") int id_users,
                                    @Field("foto_informasi") String foto_informasi,
                                    @Field("ImageName") String ImageName,
                                    @Field("judul_informasi") String judul_informasi,
                                    @Field("harga") String harga,
                                    @Field("id") int id);


    BaseApiService create(Class<BaseApiService> baseApiServiceClass);
}
