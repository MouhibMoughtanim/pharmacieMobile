package com.ensaj.pharmacielast.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;
import  androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.recyclerview.widget.RecyclerView;

import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.ZoneActivity;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.model.Zone;
import com.ensaj.pharmacielast.uiClient.gallery.GalleryFragment;
import com.ensaj.pharmacielast.viewModels.ZoneViewModel;


import java.util.ArrayList;
import java.util.List;



public class VilleAdapter extends RecyclerView.Adapter<VilleAdapter.CreditViewHolder>  {
    private static final String TAG = "CreditAdapter";
    private List<Ville> villes;
    private List<Zone> zones = new ArrayList<>();
    private Context context;



    public VilleAdapter(Context context, List<Ville> users) {
        this.villes = users;
        this.context = context;
    }



    @NonNull
    @Override
    public CreditViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.ville_item, viewGroup, false);


        // zoneViewModel= new ViewModelProvider(getActivity()).get(ZoneViewModel.class);


        final CreditViewHolder holder = new CreditViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* View popup = LayoutInflater.from(context).inflate(R.layout.popup_edit_ville, null,
                        false);

                System.out.println(((TextView) v.findViewById(R.id.idUser1)).getText().toString());



                final EditText idVilleE = popup.findViewById(R.id.idVilleE);
                final EditText nomVilleE = popup.findViewById(R.id.nomVilleE);

                idVilleE.setText(((TextView) v.findViewById(R.id.idUser1)).getText().toString());
                nomVilleE.setText(((TextView)v.findViewById(R.id.villeName)).getText().toString());

                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Notez : ")
                        .setMessage("Donner une note entre 1 et 5 :")
                        .setView(popup)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                notifyItemChanged(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .create();
                dialog.show();

                */


                String ville_id =((TextView) v.findViewById(R.id.idUser1)).getText().toString();
                System.out.println(ville_id);

                Intent zoneActivity = new Intent(context, ZoneActivity.class);
                zoneActivity.putExtra("ville_id",ville_id);
                context.startActivity(zoneActivity);


            }
        });




        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CreditViewHolder creditViewHolder, int i) {

        creditViewHolder.villeName.setText(villes.get(i).getNom());///here
        creditViewHolder.idUser1.setText(villes.get(i).getId()+"");
        Ville ville = villes.get(i);
        setZones(ville.getZones());


        Log.d(TAG, "onBindView call ! " + i);

        //System.out.println(creditViewHolder.idUser.getText().toString());


    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public List<Zone> getZones() {
        return zones;
    }

    @Override
    public int getItemCount() {
        return villes.size();
    }

    public Ville getVilleAt(int position){
        return villes.get(position);
    }




    public class CreditViewHolder extends RecyclerView.ViewHolder {
        TextView villeName;
        ConstraintLayout parent;
        Button ajouter;
        TextView done,idUser1, desc, imageName;




        public CreditViewHolder(@NonNull View itemView) {
            super(itemView);
            villeName = itemView.findViewById(R.id.villeName);
            idUser1 = itemView.findViewById(R.id.idUser1);
            parent = itemView.findViewById(R.id.parent);
            done= itemView.findViewById(R.id.done);

        }


    }




}