package com.example.asus.pict;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AdapterUserPostingList extends RecyclerView.Adapter<AdapterUserPostingList.UserPostingViewHolder> {

    private Activity mCtx;
    private List<ListUserPosting> userPostingList;

    public AdapterUserPostingList(Activity mCtx, List<ListUserPosting> userPosting) {
        this.mCtx = mCtx;
        this.userPostingList = userPosting;
    }

    @NonNull
    @Override
    public AdapterUserPostingList.UserPostingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_list_for_profile, parent, false);
        return new UserPostingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUserPostingList.UserPostingViewHolder holder, int position) {
        final ListUserPosting item = userPostingList.get(position);

        Glide.with(mCtx)
                .load("https://vegetarianmarket.000webhostapp.com/api_kuliah/uploads/" + item.getPhoto_name())
                .into(holder.imageView);

        holder.captionView.setText(item.getCaption());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mCtx, Activity_posting_user.class);
                Gson gson = new Gson();
                i.putExtra("detailData", gson.toJson(item));
                Log.d("debug","OnResponse"+ item);
                mCtx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userPostingList.size();
    }

    public void setFilter(ArrayList<ListUserPosting> filterList){
        userPostingList = new ArrayList<>();
        userPostingList.addAll(filterList);
        notifyDataSetChanged();
    }

    public class UserPostingViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        View view;
        TextView captionView;
        public UserPostingViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            imageView = itemView.findViewById(R.id.img_postingan_user);
            cardView = itemView.findViewById(R.id.cardView_posting);
            captionView = itemView.findViewById(R.id.captionprofile);
        }
    }
}
