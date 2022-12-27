package com.ensaj.pharmacielast.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.model.Ville;


import java.util.ArrayList;
import java.util.List;



public class VilleAdapter extends RecyclerView.Adapter<VilleAdapter.CreditViewHolder> {
    private static final String TAG = "CreditAdapter";
    private List<Ville> villes;
    private Context context;


    public VilleAdapter(Context context, List<Ville> users) {
        this.villes = users;
        this.context = context;
    }



    @NonNull
    @Override
    public CreditViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.ville_item, viewGroup, false);

        final CreditViewHolder holder = new CreditViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup = LayoutInflater.from(context).inflate(R.layout.popup_edit_ville, null,
                        false);

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
            }
        });




        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CreditViewHolder creditViewHolder, int i) {

        creditViewHolder.villeName.setText(villes.get(i).getNom());///here
        creditViewHolder.idUser1.setText(villes.get(i).getId()+"");


        Log.d(TAG, "onBindView call ! " + i);

        //System.out.println(creditViewHolder.idUser.getText().toString());


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