package com.example.asus.pict.Petani;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asus.pict.R;

public class InformasiActivity extends AppCompatActivity {

    LinearLayout ll_rate ;
    int i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        ll_rate = findViewById(R.id.ll_rate);

        ll_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(InformasiActivity.this);
                dialog.setContentView(R.layout.dialog_rate);
                dialog.setCanceledOnTouchOutside(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                Button btn_ok= dialog.findViewById(R.id.btn_ok);
                final ImageView star1 = dialog.findViewById(R.id.iv1);
                final ImageView star2 = dialog.findViewById(R.id.iv2);
                final ImageView star3 = dialog.findViewById(R.id.iv3);
                final ImageView star4 = dialog.findViewById(R.id.iv4);
                final ImageView star5 = dialog.findViewById(R.id.iv5);

                star1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (i==0){
                            star1.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            i =1;
                        }else{
                            star1.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            i =0;
                        }
                    }
                });
                star2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (i==0){
                            star1.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            star2.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            i =1;
                        }else{
                            star1.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            star2.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            i =0;
                        }
                    }
                });
                star3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (i==0){
                            star1.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            star2.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            star3.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            i =1;
                        }else{
                            star1.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            star2.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            star3.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            i =0;
                        }
                    }
                });
                star4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (i==0){
                            star1.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            star2.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            star3.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            star4.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            i =1;
                        }else{
                            star1.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            star2.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            star3.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            star4.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            i =0;
                        }
                    }
                });
                star5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (i==0){
                            star1.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            star2.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            star3.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            star4.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            star5.setImageDrawable(getResources().getDrawable(R.drawable.star_24));
                            i =1;
                        }else{
                            star1.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            star2.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            star3.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            star4.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            star5.setImageDrawable(getResources().getDrawable(R.drawable.startok));
                            i =0;
                        }
                    }
                });
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        Toast.makeText(InformasiActivity.this, "Terimakasih Atas Penilaian Anda", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}