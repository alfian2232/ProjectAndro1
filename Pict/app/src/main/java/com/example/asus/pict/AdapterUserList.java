package com.example.asus.pict;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.pict.Request.SearchResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdapterUserList extends RecyclerView.Adapter<AdapterUserList.UserViewHolder> {

    private Activity mCtx;
    private List<SearchResponse> userList;

    public AdapterUserList(Activity mCtx, List<SearchResponse> user) {
        this.mCtx = mCtx;
        this.userList = user;
    }

    @NonNull
    @Override
    public AdapterUserList.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUserList.UserViewHolder holder, int position) {

        Glide.with(mCtx)
                .load("https://bogelardi.000webhostapp.com/api_kuliah/fotopict/image/" + userList.get(position).getImage())
                .into(holder.imageView);

        holder.textViewTitle.setText(userList.get(position).getUsername());
        holder.textViewDescription.setText(userList.get(position).getEmail());

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(mCtx, Activity_profile_user.class);
//                Gson gson = new Gson();
//                i.putExtra("detailData", gson.toJson(item));
//                Log.d("debug","OnResponse"+ item);
//                mCtx.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setFilter(List<SearchResponse> filterList){
        userList.clear();
        userList.addAll(filterList);
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        ImageView imageView;
        CardView cardView;
        View view;
        public UserViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            textViewTitle = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.img_item_photo);
            textViewDescription = itemView.findViewById(R.id.tv_description);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
