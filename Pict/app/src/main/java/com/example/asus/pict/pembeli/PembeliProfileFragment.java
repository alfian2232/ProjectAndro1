package com.example.asus.pict.pembeli;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.asus.pict.GetStartedFirst;
import com.example.asus.pict.Petani.InformasiActivity;
import com.example.asus.pict.Petani.KebijakanAppActivity;
import com.example.asus.pict.R;

public class PembeliProfileFragment extends Fragment {
    Button btn_logout;
    LinearLayout btn_ubahprofil,btn_informasi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pembeli_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_ubahprofil = view.findViewById(R.id.btn_ubah_profil);
        btn_logout = view.findViewById(R.id.btn_logout_pembeli);
        btn_informasi = view.findViewById(R.id.btn_informasi);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data_user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id", 0);
                editor.putString("role", null);
                editor.putBoolean("sudahLogin", false);
                editor.apply();
                getActivity().finish();
                startActivity(new Intent(getContext(), GetStartedFirst.class));
            }
        });

        btn_ubahprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PembeliProfileActivity.class));
            }
        });

        LinearLayout keb_app = view.findViewById(R.id.keb_app);
        keb_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), KebijakanAppActivity.class));
            }
        });

        btn_informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InformasiActivity.class));
            }
        });

    }
}