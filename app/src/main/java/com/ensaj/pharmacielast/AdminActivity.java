package com.ensaj.pharmacielast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ensaj.pharmacielast.databinding.ActivityAdminBinding;
import com.ensaj.pharmacielast.ui.gallery.GalleryFragment;
import com.ensaj.pharmacielast.ui.home.HomeFragment;
import com.ensaj.pharmacielast.ui.slideshow.SlideshowFragment;

public class AdminActivity extends AppCompatActivity {
    ActivityAdminBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        System.out.println(getIntent().getStringExtra("user_id")+"   adminactivity");
        binding.navigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.pharmarcie_de_garde_form:
                    replaceFragment(new GalleryFragment());
                    break;
                case R.id.pharmarcie_de_garde_list:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.homeNav:
                    replaceFragment(new SlideshowFragment());

                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("user_id", getIntent().getStringExtra("user_id"));
        fragment.setArguments(bundle);
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}