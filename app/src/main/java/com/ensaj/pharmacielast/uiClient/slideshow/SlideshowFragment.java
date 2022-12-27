package com.ensaj.pharmacielast.uiClient.slideshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.adapter.VilleAdapter;
import com.ensaj.pharmacielast.databinding.FragmentSlideshowClientBinding;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.viewModels.VilleViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SlideshowFragment extends Fragment {
    private VilleViewModel villeViewModel;
    private RecyclerView recyclerView;
    private   VilleAdapter villeAdapter;

    private FragmentSlideshowClientBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowClientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycle_view);
        final FloatingActionButton button = root.findViewById(R.id.buttonF);


        villeViewModel = new ViewModelProvider(getActivity()).get(VilleViewModel.class);

        ObserveAnyChange();

        searchVilleApi();

        //Add Button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popup = LayoutInflater.from(getContext()).inflate(R.layout.popup_add_ville, null, false);
                AlertDialog dialog = new AlertDialog.Builder(getContext()).setTitle("Ville").setMessage("Veuillez entrez le nom de la ville: ").setView(popup).setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText nom = popup.findViewById(R.id.nomVille);
                        Ville ville = new Ville();
                        ville.setNom(nom.getText().toString());
                        addVille(ville);
                        searchVilleApi();
                    }
                }).setNegativeButton("Annuler", null).create();
                dialog.show();
            }
        });

        //Swipe
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(getContext(),"Ville : "+ villeAdapter.getVilleAt(viewHolder.getAdapterPosition()).getNom()+" Deleted Succesfully !", Toast.LENGTH_SHORT).show();
                deleteVille(villeAdapter.getVilleAt(viewHolder.getAdapterPosition()).getId());
            }
        }).attachToRecyclerView(recyclerView);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void ObserveAnyChange(){
        villeViewModel.getVilles().observe(getViewLifecycleOwner(), new Observer<List<Ville>>() {
            @Override
            public void onChanged(List<Ville> villes) {
                if(villes != null){

                    villeAdapter = new VilleAdapter(getContext(),villes);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(villeAdapter);

                }

            }
        });
    }

    public void searchVilleApi(){
        villeViewModel.searchVilleApi();
    }

    public void addVille(Ville ville){
        villeViewModel.addVille(ville);
    }
    public void deleteVille(int id){villeViewModel.deleteVille(id);}
}