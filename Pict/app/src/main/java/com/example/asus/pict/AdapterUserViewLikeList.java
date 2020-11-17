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
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AdapterUserViewLikeList extends RecyclerView.Adapter<AdapterUserViewLikeList.UserLikeViewHolder> {

    private Activity mCtx;
    private List<ListUser> userList;

    public AdapterUserViewLikeList(Activity mCtx, List<ListUser> user) {
        this.mCtx = mCtx;
        this.userList = user;
    }

    @NonNull
    @Override
    public AdapterUserViewLikeList.UserLikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new AdapterUserViewLikeList.UserLikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUserViewLikeList.UserLikeViewHolder holder, int position) {
        final ListUser item = userList.get(position);

        Glide.with(mCtx)
                .load("https://bogelardi.000webhostapp.com/api_kuliah/fotopict/image/" + item.getImage())
                .into(holder.imageView);

        holder.textViewTitle.setText(item.getUsername());
        holder.textViewDescription.setText(item.getEmail());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mCtx, Activity_profile_user.class);
                Gson gson = new Gson();
                i.putExtra("detailData", gson.toJson(item));
                Log.d("debug","OnResponse"+ item);
                mCtx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setFilter(ArrayList<ListUser> filterList){
        userList = new ArrayList<>();
        userList.addAll(filterList);
        notifyDataSetChanged();
    }

    public class UserLikeViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDescription;
        ImageView imageView;
        CardView cardView;
        View view;
        public UserLikeViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            textViewTitle = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.img_item_photo);
            textViewDescription = itemView.findViewById(R.id.tv_description);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
