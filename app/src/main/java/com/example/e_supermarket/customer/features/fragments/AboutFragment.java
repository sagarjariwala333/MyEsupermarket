package com.example.e_supermarket.customer.features.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_supermarket.R;


public class AboutFragment extends Fragment {


    private Toolbar tb_abt;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        tb_abt=view.findViewById(R.id.tb_abt);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_abt);


        

        tb_abt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new HomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}