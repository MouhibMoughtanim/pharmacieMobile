package com.ensaj.pharmacielast.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.model.PharmacieDeGarde;

import java.util.List;

public class PharmacieDeGardeAdapter extends RecyclerView.Adapter<PharmacieDeGardeAdapter.PharmacieDeGardeViewHolder> {
    private static final String TAG = "PharmacieDeGardeAdapter";
    private List<PharmacieDeGarde> pharmaciesDeGarde;
    private Context context;


    public PharmacieDeGardeAdapter(Context context, List<PharmacieDeGarde> pharmaciesDeGarde) {
        this.pharmaciesDeGarde = pharmaciesDeGarde;
        this.context = context;
    }



    @Override
    public PharmacieDeGardeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.pharmacie_de_garde_item, viewGroup, false);

        final PharmacieDeGardeViewHolder holder = new PharmacieDeGardeAdapter.PharmacieDeGardeViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "clicked", Toast.LENGTH_SHORT).show();
                System.out.println("clicked");
            }
        });




        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacieDeGardeViewHolder creditViewHolder, int i) {
        creditViewHolder.pharmacieName.setText(pharmaciesDeGarde.get(i).getPharmacie().getNom());///here
        creditViewHolder.gardeType.setText(pharmaciesDeGarde.get(i).getGarde().getType()+"");
        creditViewHolder.dateDebut.setText(pharmaciesDeGarde.get(i).getDateDebut()+"");
        creditViewHolder.dateFin.setText(pharmaciesDeGarde.get(i).getDateFin()+"");


        Log.d(TAG, "onBindView call ! " + i);
    }





    public int getItemCount() {
        return pharmaciesDeGarde.size();
    }

    public PharmacieDeGarde getPharmacieDeGardeAt(int position){
        return pharmaciesDeGarde.get(position);
    }

    public class PharmacieDeGardeViewHolder extends RecyclerView.ViewHolder {

        TextView pharmacieName,gardeType,dateDebut,dateFin;
        ConstraintLayout parentGarde;



        public PharmacieDeGardeViewHolder(@NonNull View itemView) {
            super(itemView);
            pharmacieName = itemView.findViewById(R.id.pharmacieName);
            gardeType = itemView.findViewById(R.id.gardeType);
            dateDebut = itemView.findViewById(R.id.dateDebut);
            dateFin = itemView.findViewById(R.id.dateFin);

            parentGarde = itemView.findViewById(R.id.parentGarde);



        }
    }
}
