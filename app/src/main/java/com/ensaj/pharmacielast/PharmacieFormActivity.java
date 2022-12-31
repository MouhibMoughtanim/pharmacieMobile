package com.ensaj.pharmacielast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.PharmacieDeGarde;
import com.ensaj.pharmacielast.model.User;
import com.ensaj.pharmacielast.ui.gallery.GalleryFragment;
import com.ensaj.pharmacielast.ui.gallery.GalleryViewModel;
import com.ensaj.pharmacielast.viewModels.PharmacieViewModel;

public class PharmacieFormActivity extends AppCompatActivity {
    private PharmacieViewModel pharmacieViewModel;
    private Button pharmacieButton;
    private EditText pharmacieNom;
    private EditText pharmacieAdresse;
    private EditText pharmacieLongitude;
    private EditText pharmacieLatitude;
    private String user_id;
    GalleryViewModel galleryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacie_form);

        pharmacieViewModel = new ViewModelProvider(this).get(PharmacieViewModel.class);
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        pharmacieNom = findViewById(R.id.pharmacie_nomA);
        pharmacieAdresse = findViewById(R.id.pharmacie_adresseA);
        pharmacieLatitude =findViewById(R.id.pharmacie_latitudeA);
        pharmacieLongitude = findViewById(R.id.pharmacie_longitudeA);
        pharmacieButton = findViewById(R.id.pharmacie_btnA);

        pharmacieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pharmacie pharmacie = new Pharmacie();

                user_id = getIntent().getStringExtra("user_id");
                System.out.println(user_id);



                pharmacie.setNom(pharmacieNom.getText().toString());
                pharmacie.setAdresse(pharmacieAdresse.getText().toString());
                pharmacie.setLat(Double.valueOf(pharmacieLatitude.getText().toString()));
                pharmacie.setLog(Double.valueOf(pharmacieLongitude.getText().toString()));


                addPharmacie(pharmacie,Integer.parseInt(user_id));

                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);

                intent.putExtra("user_id",user_id);
                intent.putExtra("pharmacie_nom",pharmacie.getNom());
                intent.putExtra("pharmacie_adresse",pharmacie.getAdresse());
                intent.putExtra("pharmacie_latitude",pharmacie.getLat());
                intent.putExtra("pharmacie_longitude",pharmacie.getLog());


                startActivity(intent);

            }
        });


    }
    public void addPharmacie(Pharmacie pharmacie,int user_id){
        pharmacieViewModel.addPharmacie(pharmacie,user_id);
    }

}