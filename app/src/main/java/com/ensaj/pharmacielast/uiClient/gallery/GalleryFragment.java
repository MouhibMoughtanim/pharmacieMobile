package com.ensaj.pharmacielast.uiClient.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ensaj.pharmacielast.MapsActivity;
import com.ensaj.pharmacielast.PharmacieActivity2;
import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.databinding.FragmentGalleryClientBinding;
import com.ensaj.pharmacielast.model.Garde;
import com.ensaj.pharmacielast.viewModels.GardeViewModel;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    private GardeViewModel gardeViewModel;
    private Spinner pharmacieGardeType;
    private TextView garde_text_id;
    private Button searchBtn;


    private FragmentGalleryClientBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryClientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        gardeViewModel = new ViewModelProvider(getActivity()).get(GardeViewModel.class);
        pharmacieGardeType = root.findViewById(R.id.pharmacie_garde_type_client);
        garde_text_id = root.findViewById(R.id.garde_text_id_client);
        searchBtn = root.findViewById(R.id.searchByGarde);

        ObserveAnyChange();
        searchGardesApi();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), ""+garde_text_id.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent pharmacieActivity2 = new Intent(getActivity(), PharmacieActivity2.class);

                pharmacieActivity2.putExtra("garde_id", garde_text_id.getText().toString());

                getActivity().startActivity(pharmacieActivity2);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
    public void searchGardesApi(){
        gardeViewModel.searchGardesApi();
    }
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
}