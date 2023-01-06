package com.ensaj.pharmacielast.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.adapter.PharmacieDeGardeAdapter;
import com.ensaj.pharmacielast.adapter.VilleAdapter;
import com.ensaj.pharmacielast.databinding.FragmentHomeBinding;
import com.ensaj.pharmacielast.model.PharmacieDeGarde;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.viewModels.PharmacieDeGardeViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;
private PharmacieDeGardeViewModel pharmacieDeGardeViewModel;
private PharmacieDeGardeAdapter pharmacieDeGardeAdapter;
private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recyclerView = root.findViewById(R.id.pharmacie_de_garde_recycle_view);

        pharmacieDeGardeViewModel = new ViewModelProvider(getActivity()).get(PharmacieDeGardeViewModel.class);
        String user_id = getArguments().getString("user_id");
        
        ObserveAnyChange();

        searchPharmaciesDeGardeByIdApi(Integer.parseInt(user_id));


        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void ObserveAnyChange(){
        pharmacieDeGardeViewModel.getPharmaciesDeGardeById().observe(getViewLifecycleOwner(), new Observer<List<PharmacieDeGarde>>() {
            @Override
            public void onChanged(List<PharmacieDeGarde> pharmacieDeGardes) {
                if(pharmacieDeGardes != null){

                    for (PharmacieDeGarde p :
                            pharmacieDeGardes) {
                        System.out.println(p.getPharmacie().getAdresse()+"  "+p.getGarde().getType()+" "+p.getDateDebut());
                    }

                    pharmacieDeGardeAdapter = new PharmacieDeGardeAdapter(getContext(),pharmacieDeGardes);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(pharmacieDeGardeAdapter);

                }

            }
        });
    }

    public void searchPharmaciesDeGardeByIdApi(int user_id){
        pharmacieDeGardeViewModel.searchPharmaciesDeGardeByIdApi(user_id);
    }
}