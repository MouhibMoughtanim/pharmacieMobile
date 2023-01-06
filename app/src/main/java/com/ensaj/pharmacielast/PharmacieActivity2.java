package com.ensaj.pharmacielast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.ensaj.pharmacielast.adapter.PharmacieAdapter;
import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.viewModels.PharmacieViewModel;

import java.util.List;

public class PharmacieActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PharmacieAdapter pharmacieAdapter;
    private PharmacieViewModel pharmacieViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacie2);


        pharmacieViewModel= new ViewModelProvider(this).get(PharmacieViewModel.class);
        recyclerView = findViewById(R.id.rvPharmacies2);

        int garde_id = Integer.parseInt(getIntent().getStringExtra("garde_id"));

        System.out.println(garde_id);

        ObserveAnyChangeGardes();

        searchPharmacieByGardeIdApi(garde_id);

    }
    private void ObserveAnyChangeGardes(){
        pharmacieViewModel.getPharmacieByGardeId().observe(this, new Observer<List<Pharmacie>>() {
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

    public void searchPharmacieByGardeIdApi(int garde_id){
        pharmacieViewModel.searchPharmacieByGardeIdApi(garde_id);
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