package com.example.asus.pict.pembeli;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.asus.pict.Fragment_Home;
import com.example.asus.pict.Fragment_Profile;
import com.example.asus.pict.Fragment_Search;
import com.example.asus.pict.R;

public class PembeliMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeli_main);
        loadFragment(new Fragment_Home());

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_pembeli);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        Fragment fragment = null;
        switch(item.getItemId()){
            case R.id.p_home:
                fragment = new PembeliHomeFragment();
                break;
            case  R.id.p_profile:
                fragment = new PembeliProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }
}