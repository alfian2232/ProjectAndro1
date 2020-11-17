package com.example.asus.pict;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Path;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.UtilsApi;
import com.google.gson.Gson;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.volley.VolleyLog.TAG;

public class AdapterPostingList extends RecyclerView.Adapter<AdapterPostingList.PostingViewHolder> {

    String email;
    ImageButton btnBeli;
    private Activity mCtx;
    private List<ListPosting> postingList;
    SharedPreferences sharedPreferences;
    String tag_json_obj = "json_obj_reg";
    BaseApiService mapiservice;

    public AdapterPostingList(Activity mCtx, List<ListPosting> posting) {
        this.mCtx = mCtx;
        this.postingList = posting;
        mapiservice = UtilsApi.getAPIService();
    }

    @NonNull
    @Override
    public AdapterPostingList.PostingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_list_for_posting, parent, false);
        return new AdapterPostingList.PostingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final AdapterPostingList.PostingViewHolder holder, int position) {
        final ListPosting item = postingList.get(position);

        holder.imageView.setBackgroundColor(Color.WHITE);
        Glide.with(mCtx)
                .load("https://vegetarianmarket.000webhostapp.com/api_kuliah/uploads/" + item.getPhoto_name())
                .into(holder.imageView);

        holder.textViewDescription.setText(item.getCaption());
        holder.textViewDescription.setText("Rp. "+item.getHarga());
        holder.textView.setText(item.getNama_users());
        holder.textView2.setText(item.getNama_users2());

        holder.imageView2.setBackgroundColor(Color.WHITE);
        Glide.with(mCtx)
                .load("https://vegetarianmarket.000webhostapp.com/api_kuliah/fotopict/image/" + item.getImage())
                .into(holder.imageView2);
        holder.imageButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plan");
                String shareBody = "https://vegetarianmarket.000webhostapp.com/api_kuliah/uploads/" + item.getPhoto_name();
                String shareSub = "Subject";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                mCtx.startActivity(Intent.createChooser(myIntent, "Share Bro!"));
            }
        });

        holder.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(mCtx,activity_update.class);
                intent1.putExtra("itemproduk",item);
                mCtx.startActivity(intent1);
            }
        });

        holder.btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = item.getEmail();
                String url = "https://api.whatsapp.com/send?phone="+ email.replaceFirst("^0+(?!$)","+62")+"&text="
                        + "Hai Saya Mau Membeli%20:%20" ;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mCtx.startActivity(i);

            }


        });

        holder.imageButtonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = mCtx.getSharedPreferences("data_user", Context.MODE_PRIVATE);
                String idUser = sharedPreferences.getString("id",null);
                String idPosting = item.getId();

                if (holder.favorite){
                    holder.favorite = false;
                    holder.imageButtonLike.setImageResource(R.drawable.ic_favorite_red);
                    mapiservice.addFavoriteRequest(idPosting.toString(), idUser.toString()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Log.i("debug", "onResponse: BERHASIL");
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().string());
                                    if (jsonObject.getString("error").equals("false")){
                                        Toast.makeText(mCtx, "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                                    }else {
                                        String error_message = jsonObject.getString("error_msg");
                                        Toast.makeText(mCtx, error_message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR >" + t.getMessage());
                            Toast.makeText(mCtx,"koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    holder.favorite = true;
                    holder.imageButtonLike.setImageResource(R.drawable.ic_favorite);
                    mapiservice.removeFavoriteRequest(idPosting.toString(), idUser.toString()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Log.i("debug", "onResponse: BERHASIL");
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().string());
                                    if (jsonObject.getString("error").equals("false")){
                                        Toast.makeText(mCtx, "Berhasil Diubah", Toast.LENGTH_SHORT).show();
                                    }else {
                                        String error_message = jsonObject.getString("error_msg");
                                        Toast.makeText(mCtx, error_message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }



                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR >" + t.getMessage());
                            Toast.makeText(mCtx,"koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(mCtx, MainActivity.class);
//                Gson gson = new Gson();
//                i.putExtra("detailData", gson.toJson(item));
//                mCtx.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return postingList.size();
    }

    public void setFilter(ArrayList<ListPosting> filterList){
        postingList = new ArrayList<>();
        postingList.addAll(filterList);
        notifyDataSetChanged();
    }


    public class PostingViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDescription;
        TextView textView, textView2;
        ImageView imageView, imageView2;
        CardView cardView;
        ImageButton imageButton,imageButtonLike,imageEdit;
        ImageButton btnBeli;
        boolean favorite = true;
        View view;
        public PostingViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            imageView = itemView.findViewById(R.id.img_item_photo_posting);
            textViewDescription = itemView.findViewById(R.id.tv_description_posting);
            cardView = itemView.findViewById(R.id.cardView_posting);
            textView = itemView.findViewById(R.id.id_namaUsers);
            imageView2 = itemView.findViewById(R.id.id_profileUser);
            textView2 = itemView.findViewById(R.id.id_namaUsers2);
            imageButton = itemView.findViewById(R.id.id_share);
            btnBeli = itemView.findViewById(R.id.id_beli);
            imageButtonLike = itemView.findViewById(R.id.id_like);
            imageEdit = itemView.findViewById(R.id.btn_edit);

        }




    }


}
