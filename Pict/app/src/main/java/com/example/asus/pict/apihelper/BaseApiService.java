package com.example.asus.pict.apihelper;

import com.example.asus.pict.ListPosting;
import com.example.asus.pict.Request.AddProdukRes;
import com.example.asus.pict.Request.EtalaseRes;
import com.example.asus.pict.Request.GetProdukRes;
import com.example.asus.pict.Request.RegResponse;
import com.example.asus.pict.Request.SearchResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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

    @FormUrlEncoded
    @POST("addproduk.php")
    Call<AddProdukRes> addprodukRequest(@Field("id_petani") int id_petani,
                                        @Field("produk") String produk,
                                        @Field("kategori") String kategori,
                                        @Field("img") String img,
                                        @Field("foto_informasi") String foto_informasi);

    @FormUrlEncoded
    @POST("addproduk1.php")
    Call<AddProdukRes> addprodukRequest1(@Field("id_petani") int id_petani,
                                        @Field("nama_produk") String nama_produk,
                                        @Field("deskripsi") String deskripsi,
                                        @Field("harga") Float harga,
                                        @Field("berat") Float berat,
                                        @Field("stok") int stok,
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
    Call<List<GetProdukRes>>getAllDataProdukByPetani(@Query("id_petani") int id);

    @FormUrlEncoded
    @POST("update_informasi.php")
    Call<AddProdukRes> updateProduk(@Field("id") int id,
                                    @Field("produk") String produk,
                                    @Field("kategori") String kategori,
                                    @Field("img") String img,
                                    @Field("foto_informasi") String judul_informasi);

    @FormUrlEncoded
    @POST("loginpembeli.php")
    Call<RegResponse> loginReqPembeli(@Field("username") String username,
                                   @Field("password") String password);

    // Fungsi ini untuk memanggil API http://192.168.43.102/pict/register.php
    @FormUrlEncoded
    @POST("registerpembeli.php")
    Call<RegResponse> regisReqPembeli(@Field("username") String username,
                                      @Field("email") String email,
                                      @Field("password") String password,
                                      @Field("nama") String nama,
                                      @Field("nomer") String nomer,
                                      @Field("alamat") String alamat);

    BaseApiService create(Class<BaseApiService> baseApiServiceClass);

    @GET("etalase.php")
    Call<EtalaseRes>getEtalase(@Query("id") int id);


    @FormUrlEncoded
    @POST("update_password.php")
    Call<AddProdukRes> updatePassword(@Field("id") int id,
                                      @Field("passlama") String passlama,
                                      @Field("passbaru") String passbaru);
    @FormUrlEncoded
    @POST("update_profil.php")
    Call<AddProdukRes> updatePembeli(@Field("id") int id,
                                      @Field("nama") String nama,
                                      @Field("nomer") String nomer,
                                     @Field("alamat") String alamat);

}
