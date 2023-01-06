package com.ensaj.pharmacielast.ui.slideshow;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ensaj.pharmacielast.DatePickerDebutFragment;
import com.ensaj.pharmacielast.DatePickerFinFragment;
import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.adapter.VilleAdapter;
import com.ensaj.pharmacielast.databinding.FragmentSlideshowBinding;
import com.ensaj.pharmacielast.model.Garde;
import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.PharmacieDeGarde;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.viewModels.GardeViewModel;
import com.ensaj.pharmacielast.viewModels.PharmacieDeGardeViewModel;
import com.ensaj.pharmacielast.viewModels.PharmacieViewModel;
import com.ensaj.pharmacielast.viewModels.VilleViewModel;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {
    private GardeViewModel gardeViewModel;
    private PharmacieDeGardeViewModel pharmacieDeGardeViewModel;
    private PharmacieViewModel pharmacieViewModel;


    private Spinner pharmacieGardeType;
    private FragmentSlideshowBinding binding;
    private Button garde_date_debut;
    private Button garde_date_fin;
    private Button garde_submit;
    private TextView garde_debut;
    private TextView garde_fin;
    private TextView garde_text_id;
    TextView pharmacie_id;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

    binding = FragmentSlideshowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        garde_date_debut = root.findViewById(R.id.garde_date_debut);
        garde_date_fin = root.findViewById(R.id.garde_date_fin);
        pharmacieGardeType = root.findViewById(R.id.pharmacie_garde_type);
        garde_submit = root.findViewById(R.id.garde_submit);
        garde_debut = root.findViewById(R.id.garde_debut);
        garde_fin = root.findViewById(R.id.garde_fin);
        garde_text_id = root.findViewById(R.id.garde_text_id);
        pharmacie_id = root.findViewById(R.id.pharmacie_id);


        gardeViewModel = new ViewModelProvider(getActivity()).get(GardeViewModel.class);
        pharmacieDeGardeViewModel = new ViewModelProvider(getActivity()).get(PharmacieDeGardeViewModel.class);
        pharmacieViewModel = new ViewModelProvider(getActivity()).get(PharmacieViewModel.class);

        String user_id = getArguments().getString("user_id");
        ObserveAnyChange();
        ObserveAnyChangePharmacieByUserId();

        searchGardesApi();
        searchPharmacieByUserApi(Integer.parseInt(user_id));

        garde_date_debut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDebutDialog(view);
            }
        });
        garde_date_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerFinDialog(view);

            }
        });
        garde_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   Garde garde = new Garde();garde.setIdGarde(Integer.parseInt(garde_text_id.getText().toString()));
                   Pharmacie pharmacie = new Pharmacie();
                   pharmacie.setId(Integer.parseInt(pharmacie_id.getText().toString()));
                   PharmacieDeGarde pharmacieDeGarde = new PharmacieDeGarde();
               // pharmacieDeGarde.setDateFin(Date.valueOf());
                // pharmacieDeGarde.setDateDebut(Date.valueOf(garde_debut.getText().toString()));
                   pharmacieDeGarde.setGarde(garde);
                   pharmacieDeGarde.setPharmacie(pharmacie);

                  addPharmacieDeGarde(pharmacieDeGarde,garde_debut.getText().toString(),garde_fin.getText().toString());

                  Toast.makeText(getContext(), ""+pharmacieDeGarde.getGarde().getIdGarde(), Toast.LENGTH_SHORT).show();
            }
        });

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;

    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //spinner adapter
    private void initspinnerfooter(List<Garde> gardes) {

        ArrayList arrayList = new ArrayList<>();
        for (Garde g: gardes) {
            arrayList.add(g.getType()+"");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        pharmacieGardeType.setAdapter(adapter);
        pharmacieGardeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                Toast.makeText(getContext(), (String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                garde_text_id.setText(gardes.get(position).getIdGarde()+"");
               // ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void ObserveAnyChange(){
        gardeViewModel.getGardes().observe(getViewLifecycleOwner(), new Observer<List<Garde>>() {
            @Override
            public void onChanged(List<Garde> gardes) {
                if(gardes != null){

                  initspinnerfooter(gardes);

                }

            }
        });
    }
    private void ObserveAnyChangePharmacieByUserId(){
        pharmacieViewModel.getPharmacieByUserId().observe(getViewLifecycleOwner(), new Observer<Pharmacie>() {
            @Override
            public void onChanged(Pharmacie pharmacie) {
                if(pharmacie != null){

                    System.out.println(pharmacie.getAdresse());
                    pharmacie_id.setText(pharmacie.getId()+"");

                }

            }
        });
    }

    public void showDatePickerDebutDialog(View v) {
        DialogFragment newFragment = new DatePickerDebutFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    public void showDatePickerFinDialog(View v) {
        DialogFragment newFragment = new DatePickerFinFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    public void searchGardesApi(){
        gardeViewModel.searchGardesApi();
    }
    public void searchPharmacieByUserApi(int user_id){
        pharmacieViewModel.searchPharmacieByUserIdApi(user_id);
    }
    public void addPharmacieDeGarde(PharmacieDeGarde pharmacieDeGarde,String debut,String fin){
        pharmacieDeGardeViewModel.addPharmacieDeGarde(pharmacieDeGarde,debut,fin);
    }



}