package com.example.asus.pict;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;
import com.example.asus.pict.apihelper.UtilsApi;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Activity_posting_user extends AppCompatActivity {

    public static final String URL = "https://vegetarianmarket.000webhostapp.com/api_kuliah/";
    ImageButton idBack;
    TextView textViewUser, textViewUser2, textViewCaption;
    ImageView imageViewProfile, imageViewPosting;
    CardView viewLikes;
    int ids;
    private BaseApiService apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_user);
        textViewUser2 = (TextView) findViewById(R.id.id_namaUsers2);
        textViewCaption = (TextView) findViewById(R.id.tv_description_posting);
        imageViewPosting = (ImageView) findViewById(R.id.img_item_photo_posting);
        final Gson gson = new Gson();
        final ListPosting listPosting = gson.fromJson(getIntent().getStringExtra("detailData"), ListPosting.class);

        ids = Integer.parseInt(listPosting.getId());
        String namaUser2 = listPosting.getNama_users2();
        textViewUser2.setText(namaUser2);
        String caption = listPosting.getCaption();
        textViewCaption.setText(caption);

        Glide.with(getApplicationContext())
                .load("https://vegetarianmarket.000webhostapp.com/api_kuliah/uploads/" + listPosting.getPhoto_name())
                .into(imageViewPosting);

//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), Activity_view_likes.class);
//                Gson gson = new Gson();
//                i.putExtra("detailData", gson.toJson(listPosting));
//                Log.d("debug","OnResponse"+ listPosting);
//                getApplicationContext().startActivity(i);
//            });

        viewLikes = (CardView) findViewById(R.id.view_like);
        viewLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), Activity_view_likes.class);
//                Gson gson = new Gson();
//                i.putExtra("detailData", gson.toJson(listPosting));
//                Log.d("debug","OnResponse"+ listPosting);
//                getApplicationContext().startActivity(i);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Activity_posting_user.this);
                alertDialogBuilder.setTitle("Peringatan");
                alertDialogBuilder
                        .setMessage("Apakah Anda yakin ingin mengapus data ini?")
                        .setCancelable(false)
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                BaseApiService api = retrofit.create(BaseApiService.class);
                                Call<ListPosting> call = api.hapus(ids);
                                call.enqueue(new Callback<ListPosting>() {
                                    @Override
                                    public void onResponse(Call<ListPosting> call, Response<ListPosting> response) {
                                        String message = response.body().getMessage();
                                        if (response.isSuccessful()) {
                                            Toast.makeText(Activity_posting_user.this, message, Toast.LENGTH_SHORT).show();
                                            startActivityForResult(new Intent(Activity_posting_user.this,MainActivity.class),13);
                                        } else {
                                            Toast.makeText(Activity_posting_user.this, message, Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ListPosting> call, Throwable t) {
                                        t.printStackTrace();
                                        Toast.makeText(Activity_posting_user.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }



//
//    private void hapusData(String detailData, String toJson) {
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Deleting...");
//        progressDialog.show();
//
//
//        apiInterface = UtilsApi.getAPIService().create(BaseApiService.class);
//
//        Call<ListPosting> call = apiInterface.deletePet(this.id);
//
//        call.enqueue(new Callback<ListPosting>() {
//            @Override
//            public void onResponse(Call<ListPosting> call, Response<ListPosting> response) {
//
//                progressDialog.dismiss();
//
//                Log.i(Activity_posting_user.class.getSimpleName(), response.toString());
//
//                String value = response.body().getId();
//
//                if (value.equals("1")){
//                    Toast.makeText(Activity_posting_user.this, value, Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toast.makeText(Activity_posting_user.this, value, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ListPosting> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(Activity_posting_user.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//


}
