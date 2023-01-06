package com.ensaj.pharmacielast.adapter;

import android.app.Activity;
import android.content.Context;
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

import com.ensaj.pharmacielast.PharmacieActivity;
import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.ZoneActivity;
import com.ensaj.pharmacielast.model.Pharmacie;
import com.ensaj.pharmacielast.model.Ville;
import com.ensaj.pharmacielast.model.Zone;
import com.ensaj.pharmacielast.viewModels.ZoneViewModel;

import java.util.ArrayList;
import java.util.List;

public class ZoneAdapter extends  RecyclerView.Adapter<ZoneAdapter.ZoneViewHolder> implements Filterable {
    private static final String TAG = "ZoneAdapter";
    private List<Zone> zones;
    private Context context;
    private List<Zone> zonesFilter;
    private NewFilter mfilter;


    public ZoneAdapter(Context context, List<Zone> zones) {
        this.zones = zones;
        this.context = context;
        zonesFilter = new ArrayList<>();
        zonesFilter.addAll(zones);
        mfilter = new NewFilter(this);
    }



    @NonNull
    @Override
    public ZoneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.zone_item, viewGroup, false);



        final ZoneViewHolder holder = new ZoneViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String zone_id =((TextView) v.findViewById(R.id.idZone)).getText().toString();
                System.out.println(zone_id);

                 Intent pharmacieActivity = new Intent(context, PharmacieActivity.class);
                 pharmacieActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 pharmacieActivity.putExtra("zone_id",zone_id);
                 context.startActivity(pharmacieActivity);

            }
        });




        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ZoneViewHolder zoneViewHolder, int i) {

        zoneViewHolder.zoneName.setText(zonesFilter.get(i).getNom());///here
        zoneViewHolder.idZone.setText(zonesFilter.get(i).getId()+"");
        Zone zone = zonesFilter.get(i);

        Log.d(TAG, "onBindView call ! " + i);

        //System.out.println(ZoneViewHolder.idUser.getText().toString());


    }



    @Override
    public int getItemCount() {
        return zonesFilter.size();
    }

    public Zone getZoneAt(int position){
        return zonesFilter.get(position);
    }

    @Override
    public Filter getFilter() {
        return mfilter;
    }

    public class ZoneViewHolder extends RecyclerView.ViewHolder {

        TextView zoneName;
        ConstraintLayout parent;
        Button ajouter;
        TextView done,idZone, desc, imageName;
        TextView idss;



        public ZoneViewHolder(@NonNull View itemView) {
            super(itemView);
            zoneName = itemView.findViewById(R.id.zoneName);
            idZone = itemView.findViewById(R.id.idZone);
            parent = itemView.findViewById(R.id.parent);
            done= itemView.findViewById(R.id.done);

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
            zonesFilter.clear();
            final FilterResults results = new FilterResults();
            if (charSequence.length() == 0) {
                zonesFilter.addAll(zones);
            } else {
                final String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Zone p : zones) {
                    if (p.getNom().toLowerCase().startsWith(filterPattern)) {
                        zonesFilter.add(p);
                    }
                }
            }
            results.values = zonesFilter;
            results.count = zonesFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            zonesFilter = (List<Zone>) filterResults.values;
            this.mAdapter.notifyDataSetChanged();
        }
    }


}
