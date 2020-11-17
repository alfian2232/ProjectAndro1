package com.example.asus.pict;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class Activity_profile_user extends AppCompatActivity {

    ImageButton idBack;
    TextView textViewUser, textViewEmail, textViewTitle;
    ImageView imageView;
    private RecyclerView recyclerView;
    List<ListProfilePosting> profilePostingList;
    AdapterProfilePostingList adapter;
    ProgressDialog pDialog;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        textViewUser = (TextView) findViewById(R.id.IdTextname2);
        textViewEmail = (TextView) findViewById(R.id.IdTextemail2);
        textViewTitle = (TextView) findViewById(R.id.id_title_user);
        imageView = (ImageView) findViewById(R.id.idprofileUser);
        Gson gson = new Gson();
        ListUser listUser = gson.fromJson(getIntent().getStringExtra("detailData"), ListUser.class);

        String profileUser = listUser.getUsername();
        textViewUser.setText(profileUser);
        String profileEmail = listUser.getEmail();
        textViewEmail.setText(profileEmail);
        String profileTitle = listUser.getUsername();
        textViewTitle.setText(profileTitle);
        Glide.with(getApplicationContext())
                .load("https://bogelardi.000webhostapp.com/api_kuliah/fotopict/image/"+listUser.getImage())
                .into(imageView);


        idBack = (ImageButton) findViewById(R.id.btn_kembali_users);
        idBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity_profile_user.this.finish();
            }
        });

        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView = findViewById(R.id.rv_items_postingan_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        profilePostingList = new ArrayList<>();

        adapter = new AdapterProfilePostingList(this, profilePostingList);
        loadList();
    }

    private void loadList() {
        sharedPreferences = this.getSharedPreferences("data_user", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id",null);
        Gson gson = new Gson();
        ListUser listUser = gson.fromJson(getIntent().getStringExtra("detailData"), ListUser.class);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://pictandroid.000webhostapp.com/list_posting_user.php?id_user="+listUser.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject product = array.getJSONObject(i);

                                profilePostingList.add(new ListProfilePosting(
                                        product.getString("id"),
                                        product.getString("photo_name"),
                                        product.getString("caption"),
                                        product.getString("nama_users"),
                                        product.getString("image"),
                                        product.getString("nama_users2")
                                ));
                            }
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Login Error: " + error.getMessage());
                        Toast.makeText(Activity_profile_user.this,
                                error.getMessage(), Toast.LENGTH_LONG).show();

                        hideDialog();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
