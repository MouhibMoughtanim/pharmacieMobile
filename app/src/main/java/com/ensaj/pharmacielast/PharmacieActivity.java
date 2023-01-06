package com.ensaj.pharmacielast;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.ensaj.pharmacielast.adapter.PharmacieAdapter;
import com.ensaj.pharmacielast.adapter.ZoneAdapter;
import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.Zone;
import com.ensaj.pharmacielast.viewModels.PharmacieViewModel;
import com.ensaj.pharmacielast.viewModels.ZoneViewModel;

import java.util.ArrayList;
import java.util.List;

public class PharmacieActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PharmacieAdapter pharmacieAdapter;
    private PharmacieViewModel pharmacieViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacie);


        pharmacieViewModel= new ViewModelProvider(this).get(PharmacieViewModel.class);
        recyclerView = findViewById(R.id.rvPharmacies);

        int zone_id = Integer.parseInt(getIntent().getStringExtra("zone_id"));

        System.out.println(zone_id);

        ObserveAnyChangeZones();

        searchPharmacieByZoneIdApi(zone_id);

    }




    private void ObserveAnyChangeZones(){
        pharmacieViewModel.getPharmacieByZoneId().observe(this, new Observer<List<Pharmacie>>() {
            @Override
            public void onChanged(List<Pharmacie> pharmacies) {
                if(pharmacies != null){
                    for (Pharmacie p : pharmacies
                         ) {
                        System.out.println(p.getNom());
                    }

                    pharmacieAdapter = new PharmacieAdapter(getApplicationContext(),pharmacies );
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(pharmacieAdapter);

                }

            }
        });
    }

    public void searchPharmacieByZoneIdApi(int zone_id){
        pharmacieViewModel.searchPharmacieByZoneIdApi(zone_id);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (pharmacieAdapter != null){
                    pharmacieAdapter.getFilter().filter(newText);
                }
                return true;
            }

        });
        return true;
    }

}