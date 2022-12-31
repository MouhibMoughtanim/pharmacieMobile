package com.ensaj.pharmacielast.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ensaj.pharmacielast.Main2Activity;
import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.databinding.FragmentGalleryBinding;
import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.viewModels.PharmacieViewModel;

public class GalleryFragment extends Fragment {

    private PharmacieViewModel pharmacieViewModel;
    private Button pharmacieButton;
    private EditText pharmacieNom;
    private EditText pharmacieAdresse;
    private EditText pharmacieLongitude;
    private EditText pharmacieLatitude;

private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

    binding = FragmentGalleryBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    pharmacieViewModel = new ViewModelProvider(getActivity()).get(PharmacieViewModel.class);
        String user_id = getArguments().getString("user_id");
        System.out.println(user_id +"   fragment 3la slamtak");

        pharmacieNom = root.findViewById(R.id.pharmacie_nom);
    pharmacieAdresse = root.findViewById(R.id.pharmacie_adresse);
    pharmacieLatitude = root.findViewById(R.id.pharmacie_latitude);
    pharmacieLongitude = root.findViewById(R.id.pharmacie_longitude);
    pharmacieButton = root.findViewById(R.id.pharmacie_btn);

    pharmacieButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Pharmacie pharmacie = new Pharmacie();
            pharmacie.setNom(pharmacieNom.getText().toString());
            pharmacie.setAdresse(pharmacieAdresse.getText().toString());
            pharmacie.setLat(Double.valueOf(pharmacieLatitude.getText().toString()));
            pharmacie.setLog(Double.valueOf(pharmacieLongitude.getText().toString()));

            addPharmacie(pharmacie,Integer.parseInt(user_id));

        }
    });


        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void addPharmacie(Pharmacie pharmacie,int user_id){
        pharmacieViewModel.addPharmacie(pharmacie,user_id);
    }


}