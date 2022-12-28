package com.ensaj.pharmacielast.ui.slideshow;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ensaj.pharmacielast.DatePickerFragment;
import com.ensaj.pharmacielast.R;
import com.ensaj.pharmacielast.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {
    private Spinner pharmacieGardeType;
    private FragmentSlideshowBinding binding;
    private Button garde_date_debut;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

    binding = FragmentSlideshowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        garde_date_debut = root.findViewById(R.id.garde_date_debut);
        pharmacieGardeType = root.findViewById(R.id.pharmacie_garde_type);
        initspinnerfooter();

        garde_date_debut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;

    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initspinnerfooter() {
        String[] items = new String[]{
                "Choose apple", "Choose boy", " Choose cat", "Choose dog",
        };
        ArrayList arrayList = new ArrayList<>();
        for (String s: items) {
            arrayList.add(s);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        pharmacieGardeType.setAdapter(adapter);
        pharmacieGardeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }


}