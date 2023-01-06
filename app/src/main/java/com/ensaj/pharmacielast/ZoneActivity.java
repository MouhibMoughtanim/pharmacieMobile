package com.ensaj.pharmacielast;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.SearchView;

import com.ensaj.pharmacielast.adapter.VilleAdapter;
import com.ensaj.pharmacielast.adapter.ZoneAdapter;
import com.ensaj.pharmacielast.model.Zone;
import com.ensaj.pharmacielast.viewModels.ZoneViewModel;

import java.util.List;

public class ZoneActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ZoneAdapter zoneAdapter;
    private ZoneViewModel zoneViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);

        zoneViewModel= new ViewModelProvider(this).get(ZoneViewModel.class);
        recyclerView = findViewById(R.id.zone_recycle_view);

        int ville_id = Integer.parseInt(getIntent().getStringExtra("ville_id"));

        System.out.println(ville_id);

        ObserveAnyChangeZones();

        searchZoneApi(ville_id);


    }
    private void ObserveAnyChangeZones(){
        zoneViewModel.getZones().observe(this, new Observer<List<Zone>>() {
            @Override
            public void onChanged(List<Zone> zones) {
                if(zones != null){


                        zoneAdapter = new ZoneAdapter(getApplicationContext(),zones );
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(zoneAdapter);

                }

            }
        });
    }

    public void searchZoneApi(int ville_id){
        zoneViewModel.searchZonesApi(ville_id);
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
                if (zoneAdapter != null){
                    zoneAdapter.getFilter().filter(newText);
                }
                return true;
            }

        });
        return true;
    }
}