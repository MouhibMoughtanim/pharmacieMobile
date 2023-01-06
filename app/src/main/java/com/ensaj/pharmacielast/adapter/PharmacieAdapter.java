package com.ensaj.pharmacielast.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.ensaj.pharmacielast.MapsActivity;
import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.viewModels.PharmacieViewModel;

import java.util.ArrayList;
import java.util.List;

public class PharmacieAdapter extends RecyclerView.Adapter<PharmacieAdapter.PharmacieViewHolder> implements Filterable {

    private static final String TAG = "ZoneAdapter";
    private List<Pharmacie> pharmacies;
    private List<Pharmacie> pharmaciesFilter;
    private NewFilter mfilter;

    private PharmacieViewModel pharmacieViewModel;
    private Context context;


    public PharmacieAdapter(Context context, List<Pharmacie> pharmacies) {
        this.pharmacies = pharmacies;
        this.context = context;
        pharmaciesFilter = new ArrayList<>();
        pharmaciesFilter.addAll(pharmacies);
        mfilter = new NewFilter(this);
    }


    @NonNull
    @Override
    public PharmacieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.list_item, viewGroup, false);


        final PharmacieViewHolder holder = new PharmacieViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pharmacie_id = ((TextView) v.findViewById(R.id.idPharmacie)).getText().toString();
                String longitudePharmacie = ((TextView) v.findViewById(R.id.longitudePharmacie)).getText().toString();
                String latitudePharmacie = ((TextView) v.findViewById(R.id.latitudePharmacie)).getText().toString();
                String nomPharmacie = ((TextView) v.findViewById(R.id.nomPharmacie)).getText().toString();

                System.out.println(pharmacie_id);
                System.out.println(latitudePharmacie);
                System.out.println(longitudePharmacie);

                Intent mapsActivity = new Intent(context, MapsActivity.class);

                mapsActivity.putExtra("nom", nomPharmacie);
                mapsActivity.putExtra("latitude", latitudePharmacie);
                mapsActivity.putExtra("longitude", longitudePharmacie);

                context.startActivity(mapsActivity);

            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacieViewHolder pharmacieViewHolder, int i) {

        pharmacieViewHolder.nomPharmacie.setText(pharmaciesFilter.get(i).getNom());///here
        pharmacieViewHolder.gardePharmacie.setText("24/24");
        //pharmacieViewHolder.zonePharmacie.setText("24/24");
        // pharmacieViewHolder.telephonePharmacie.setText("24/24");
        pharmacieViewHolder.adressePharmacie.setText(pharmaciesFilter.get(i).getAdresse() + "");
        pharmacieViewHolder.idPharmacie.setText(pharmaciesFilter.get(i).getId() + "");
        pharmacieViewHolder.longitudePharmacie.setText(pharmaciesFilter.get(i).getLog() + "");
        pharmacieViewHolder.latitudePharmacie.setText(pharmaciesFilter.get(i).getLat() + "");

        Log.d(TAG, "onBindView call ! " + i);

        //System.out.println(ZoneViewHolder.idUser.getText().toString());


    }


    @Override
    public int getItemCount() {
        return pharmaciesFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mfilter;
    }


    public class PharmacieViewHolder extends RecyclerView.ViewHolder {
        TextView adressePharmacie;
        TextView nomPharmacie;
        TextView gardePharmacie;
        TextView latitudePharmacie;
        TextView longitudePharmacie;
        TextView idPharmacie;
        //TextView telephonePharmacie;
        //TextView zonePharmacie;


        ConstraintLayout parent;
        Button ajouter;
        TextView done, idZone, desc, imageName;


        public PharmacieViewHolder(@NonNull View itemView) {
            super(itemView);

            nomPharmacie = itemView.findViewById(R.id.nomPharmacie);
            gardePharmacie = itemView.findViewById(R.id.gardePharmacie);
            adressePharmacie = itemView.findViewById(R.id.adressePharmacie);
            idPharmacie = itemView.findViewById(R.id.idPharmacie);
            latitudePharmacie = itemView.findViewById(R.id.latitudePharmacie);
            longitudePharmacie = itemView.findViewById(R.id.longitudePharmacie);
            //  telephonePharmacie = itemView.findViewById(R.id.telephonePharmacie);
            // zonePharmacie = itemView.findViewById(R.id.zonePharmacie);

            parent = itemView.findViewById(R.id.parent);
            done = itemView.findViewById(R.id.done);

        }


    }

    public class NewFilter extends Filter {
        public RecyclerView.Adapter mAdapter;

        public NewFilter(RecyclerView.Adapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            pharmaciesFilter.clear();
            final FilterResults results = new FilterResults();
            if (charSequence.length() == 0) {
                pharmaciesFilter.addAll(pharmacies);
            } else {
                final String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Pharmacie p : pharmacies) {
                    if (p.getNom().toLowerCase().startsWith(filterPattern)) {
                        pharmaciesFilter.add(p);
                    }
                }
            }
            results.values = pharmaciesFilter;
            results.count = pharmaciesFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            pharmaciesFilter = (List<Pharmacie>) filterResults.values;
            this.mAdapter.notifyDataSetChanged();
        }
    }

}