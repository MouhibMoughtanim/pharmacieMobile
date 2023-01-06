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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ensaj.pharmacielast.Main2Activity;
import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.databinding.FragmentGalleryBinding;
import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.viewModels.PharmacieViewModel;

import org.w3c.dom.Text;

public class GalleryFragment extends Fragment {

    private PharmacieViewModel pharmacieViewModel;
    private EditText pharmacieNom;
    private EditText pharmacieAdresse;
    private EditText pharmacieLongitude;
    private EditText pharmacieLatitude;
    private TextView pharmacieStatus;

private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

    binding = FragmentGalleryBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    pharmacieViewModel = new ViewModelProvider(getActivity()).get(PharmacieViewModel.class);
        String user_id = getArguments().getString("user_id");
        System.out.println(user_id +"   fragment 3la slamtak");

        ObserveAnyChangePharmacieByUserId();

        searchPharmacieByUserApi(Integer.parseInt(user_id));

        pharmacieNom = root.findViewById(R.id.pharmacie_nom);
        pharmacieAdresse = root.findViewById(R.id.pharmacie_adresse);
        pharmacieLatitude = root.findViewById(R.id.pharmacie_latitude);
        pharmacieLongitude = root.findViewById(R.id.pharmacie_longitude);
        pharmacieStatus = root.findViewById(R.id.pharmacie_status);







        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void ObserveAnyChangePharmacieByUserId(){
        pharmacieViewModel.getPharmacieByUserId().observe(getViewLifecycleOwner(), new Observer<Pharmacie>() {
            @Override
            public void onChanged(Pharmacie pharmacie) {
                if(pharmacie != null){

                    System.out.println(pharmacie.getAdresse());
                    pharmacieNom.setText(pharmacie.getNom());
                    pharmacieAdresse.setText(pharmacie.getAdresse());
                    pharmacieLatitude.setText(pharmacie.getLat()+"");
                    pharmacieLongitude.setText(pharmacie.getLog()+"");
                    //pharmacieStatusHelper.setText(pharmacie.getEtat()+"");
                    if(pharmacie.getEtat() == 0){
                        pharmacieStatus.setText("EN ATTENTE DE CONFIRMATION");
                    }else if(pharmacie.getEtat() == 1){
                        pharmacieStatus.setText("ACCEPTE");

                    }else if(pharmacie.getEtat() == 2){
                        pharmacieStatus.setText("REFUSE");

                    }

                }

            }
        });
    }

    public void addPharmacie(Pharmacie pharmacie,int user_id){
        pharmacieViewModel.addPharmacie(pharmacie,user_id);
    }
    public void searchPharmacieByUserApi(int user_id){
        pharmacieViewModel.searchPharmacieByUserIdApi(user_id);
    }


}