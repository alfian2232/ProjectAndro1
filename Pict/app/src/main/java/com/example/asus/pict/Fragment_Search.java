package com.example.asus.pict;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAssignedNumbers;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.pict.Request.SearchResponse;
import com.example.asus.pict.Request.SerachAdapter;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.RetrofitClient;
import com.example.asus.pict.apihelper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.volley.VolleyLog.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Search extends Fragment {

    private RecyclerView recyclerView;
    List<ListUser> userList;
    AdapterPostingList adapter;
    ProgressDialog pDialog;
    Toolbar toolbar;
    List<ListPosting> SearchRes=new ArrayList<>();;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);
        toolbar = view.findViewById(R.id.toolbar_search);

        recyclerView = view.findViewById(R.id.rv_items_home);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        loadList();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        final MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setIcon(R.drawable.ic_search);
        SearchView searchView  = new SearchView(getContext());
        searchView.setQueryHint("Search....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String nextText) {
                nextText = nextText.toLowerCase();
                List<ListPosting> searchResponse = new ArrayList<>();
                for(ListPosting data : SearchRes){
                    String nama = data.getNama_users2().toLowerCase();
                    if(nama.contains(nextText)){
                        searchResponse.add(data);
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new AdapterPostingList(getActivity(),searchResponse);
                recyclerView.setAdapter(adapter);
//                SerachAdapter serachAdapter = new SerachAdapter(getContext(),SearchRes);
//                serachAdapter.getFilter().filter(nextText);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                nextText = nextText.toLowerCase();
                List<ListPosting> searchResponse = new ArrayList<>();
                for(ListPosting data : SearchRes){
                    String nama = data.getNama_users2().toLowerCase();
                    if(nama.contains(nextText)){
                        searchResponse.add(data);
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new AdapterPostingList(getActivity(),searchResponse);
                recyclerView.setAdapter(adapter);
                return true;
            }
        });
        searchItem.setActionView(searchView);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void loadList() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");

        BaseApiService service = RetrofitClient.getClient().create(BaseApiService.class);
        Call<List<ListPosting>> call = service.getAllDataProduk();
        call.enqueue(new Callback<List<ListPosting>>() {
            @Override
            public void onResponse(Call<List<ListPosting>> call, Response<List<ListPosting>> response) {
                SearchRes = response.body();
            }

            @Override
            public void onFailure(Call<List<ListPosting>> call, Throwable t) {

            }
        });

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
