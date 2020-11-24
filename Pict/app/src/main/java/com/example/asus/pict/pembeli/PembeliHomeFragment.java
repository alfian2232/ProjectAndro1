package com.example.asus.pict.pembeli;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.asus.pict.Adapter.EtalaseListAdapter;
import com.example.asus.pict.Adapter.ProdukLimitAdapter;
import com.example.asus.pict.R;
import com.example.asus.pict.Request.EtalaseRes;
import com.example.asus.pict.Request.ProdukLimitRes;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembeliHomeFragment extends Fragment {
    ProgressDialog pDialog;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    ProdukLimitAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pembeli_home, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_allproduk);


        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        pDialog.show();
        BaseApiService service = RetrofitClient.getClient1().create(BaseApiService.class);
        Call<ProdukLimitRes> call = service.getProdukLimit();
        call.enqueue(new Callback<ProdukLimitRes>() {
            @Override
            public void onResponse(Call<ProdukLimitRes> call, Response<ProdukLimitRes> response) {
                if (!response.body().getError()){
                    gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    adapter = new ProdukLimitAdapter(getActivity(),response.body());
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(getContext(), "Load Gagal", Toast.LENGTH_SHORT).show();
                }
                pDialog.cancel();
            }

            @Override
            public void onFailure(Call<ProdukLimitRes> call, Throwable t) {

            }
        });
    }
}