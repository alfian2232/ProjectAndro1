package com.example.asus.pict;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.pict.Request.SearchResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class Activity_view_likes extends AppCompatActivity {

    ImageButton idBack;
    private RecyclerView recyclerView;
    List<SearchResponse> userList;
    AdapterUserList adapter;
    ProgressDialog pDialog;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_likes);
        toolbar = findViewById(R.id.toolbar_like);
        (this).setSupportActionBar(toolbar);
        (this).getSupportActionBar().setDisplayShowTitleEnabled(true);
        Gson gson = new Gson();
        ListPosting listPosting = gson.fromJson(getIntent().getStringExtra("detailData"), ListPosting.class);

        idBack = (ImageButton) findViewById(R.id.btn_kembali_like);
        idBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity_view_likes.this.finish();
            }
        });

        recyclerView = findViewById(R.id.rv_items_like);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();

        adapter = new AdapterUserList(this, userList);
//        loadList();

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setIcon(R.drawable.ic_search);
        SearchView searchView  = new SearchView(this);
        searchView.setQueryHint("Search....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                nextText = nextText.toLowerCase();
                List<SearchResponse> filter = new ArrayList<>();
                for(SearchResponse data : userList){
                    String nama = data.getUsername().toLowerCase();
                    if(nama.contains(nextText)){
                        filter.add(data);
                    }
                }
                adapter.setFilter(filter);
                return true;
            }
        });
        searchItem.setActionView(searchView);
        return super.onCreateOptionsMenu(menu);
    }



//    private void loadList() {
//        sharedPreferences = this.getSharedPreferences("data_user", Context.MODE_PRIVATE);
//        String id = sharedPreferences.getString("id",null);
//        Gson gson = new Gson();
//        ListPosting listPosting = gson.fromJson(getIntent().getStringExtra("detailData"), ListPosting.class);
//        pDialog = new ProgressDialog(this);
//        pDialog.setCancelable(false);
//        pDialog.setMessage("Loading ...");
//        showDialog();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://bogelardi.000webhostapp.com/api_kuliah/view_like.php?id_posting="+listPosting.getId(),
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        hideDialog();
//                        try {
//                            JSONArray array = new JSONArray(response);
//
//                            for (int i = 0; i < array.length(); i++) {
//
//                                JSONObject product = array.getJSONObject(i);
//
//                                userList.add(new ListUser(
//                                        product.getString("id"),
//                                        product.getString("username"),
//                                        product.getString("email"),
//                                        product.getString("image"),
//                                        product.getString("jumlahlike")
//                                ));
//                            }
//                            recyclerView.setAdapter(adapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Login Error: " + error.getMessage());
//                        Toast.makeText(Activity_view_likes.this,
//                                error.getMessage(), Toast.LENGTH_LONG).show();
//
//                        hideDialog();
//                    }
//                });
//
//        Volley.newRequestQueue(this).add(stringRequest);
//    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



}
