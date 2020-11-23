package com.example.asus.pict;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.asus.pict.Adapter.EtalaseListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Profile extends Fragment {

    private RecyclerView recyclerView;
    List<ListUserPosting> userPostingList;
    EtalaseListAdapter adapter;
    ProgressDialog pDialog;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);
        toolbar = view.findViewById(R.id.toolbar_profile);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        FloatingActionButton b = (FloatingActionButton) view.findViewById(R.id.id_posting);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), activity_posting.class);
                startActivity(intent);
            }
        });
        sharedPreferences = getContext().getSharedPreferences("data_user",Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id",null);
        String nama = sharedPreferences.getString("nama", null);
        String email = sharedPreferences.getString("email", null);
        String gambar = sharedPreferences.getString("gambar", null);
        TextView namaprofile = view.findViewById(R.id.IdTextname);
        namaprofile.setText(sharedPreferences.getString("nama",nama));
        TextView emailprofile = view.findViewById(R.id.IdTextemail);
        emailprofile.setText(sharedPreferences.getString("email",email));
        ImageView gambarprofile = view.findViewById(R.id.idprofile2);
        Glide.with(getContext())
                .load("https://vegetarianmarket.000webhostapp.com/api_kuliah/fotopict/image/"+gambar)
                .into(gambarprofile);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView = view.findViewById(R.id.rv_items_postingan_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        userPostingList = new ArrayList<>();

//        adapter = new EtalaseListAdapter(getActivity(), userPostingList);
        loadList();
    }

    private void loadList() {
        sharedPreferences = getContext().getSharedPreferences("data_user",Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id",null);
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://vegetarianmarket.000webhostapp.com/api_kuliah/list_posting_user.php?id_user="+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject product = array.getJSONObject(i);

                                userPostingList.add(new ListUserPosting(
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
                        Toast.makeText(getContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();

                        hideDialog();
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_logout:
                dialogLogout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void dialogLogout() {
        AlertDialog.Builder dialogLogout = new AlertDialog.Builder(getContext());
        dialogLogout.setMessage("Apakah anda ingin keluar?");
        dialogLogout.setTitle("Keluar");
        dialogLogout.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id", null);
                editor.putString("nama",null);
                editor.putString("email",null);
                editor.putBoolean("sudahLogin", false);
                editor.apply();
                Intent intent = new Intent(getContext(), Activity_Login.class);
                startActivity(intent);
            }
        });
        dialogLogout.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogLogout.show();
    }
}
