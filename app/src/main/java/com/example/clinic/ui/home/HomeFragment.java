package com.example.clinic.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.clinic.AdapterDepart;
import com.example.clinic.Doctors;
import com.example.clinic.R;
import com.example.clinic.Reservation;
import com.example.clinic.doctorSections;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static    String doctorName =null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        doctorSections sec = new doctorSections();
        Doctors doc ;
        AdapterDepart ad ;
        List<Doctors> data;
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
       // final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
        data = new ArrayList<>(sec.getData());
        ad = new AdapterDepart(getActivity(), (ArrayList<Doctors>) data);
        ListView gvdoc = root.findViewById(R.id.listdoc);
        gvdoc.setAdapter(ad);
        gvdoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                   doctorName = data.get(i).getName();
                   //Log.d("dd",doctorName);
                    startActivity(new Intent(getActivity(), Reservation.class));

            }
        });
        return root;
    }

}