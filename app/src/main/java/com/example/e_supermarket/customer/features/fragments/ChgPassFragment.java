package com.example.e_supermarket.customer.features.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_supermarket.R;

public class ChgPassFragment extends Fragment {


    private Button btn_chgpass1;
    private TextView tv_fgpass;

    public ChgPassFragment() {
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
        View view = inflater.inflate(R.layout.fragment_chg_pass, container, false);
        btn_chgpass1=view.findViewById(R.id.btn_chgpass1);
        tv_fgpass=view.findViewById(R.id.tv_fgpass);

        btn_chgpass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Password Changed", Toast.LENGTH_SHORT).show();
            }
        });

        tv_fgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new FgPassFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}