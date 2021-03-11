package com.example.e_supermarket.customer.staff.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.staff.adapters.StaffVcustAdapter;
import com.example.e_supermarket.customer.staff.models.Vcust_model;

import java.util.ArrayList;

public class StaffVcustFragment extends Fragment {


    private RecyclerView rv_vcust;
    private ArrayList<Vcust_model> list;
    private StaffVcustAdapter mAdapter;


    public StaffVcustFragment() {
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
        View view = inflater.inflate(R.layout.fragment_staff_vcust, container, false);



        rv_vcust=view.findViewById(R.id.rv_vcust);


        setdata();
        setadapter();

        return view;
    }

    private void setadapter() {


        mAdapter=new StaffVcustAdapter(StaffVcustFragment.this,list);
        rv_vcust.setAdapter(mAdapter);
        rv_vcust.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void setdata()
    {
        list=new ArrayList<>();

        Vcust_model model1=new Vcust_model();
        model1.setImg_vcust(R.drawable.ic_launcher_background);
        model1.setId_vcust("C00001");
        model1.setName_vcust("XYZ");
        model1.setNum_vcust("9999999999");
        list.add(model1);

        Vcust_model model2=new Vcust_model();
        model2.setImg_vcust(R.drawable.ic_launcher_background);
        model2.setId_vcust("C00001");
        model2.setName_vcust("XYZ");
        model2.setNum_vcust("9999999999");
        list.add(model2);

        Vcust_model model3=new Vcust_model();
        model3.setImg_vcust(R.drawable.ic_launcher_background);
        model3.setId_vcust("C00001");
        model3.setName_vcust("XYZ");
        model3.setNum_vcust("9999999999");
        list.add(model3);

        Vcust_model model4=new Vcust_model();
        model4.setImg_vcust(R.drawable.ic_launcher_background);
        model4.setId_vcust("C00001");
        model4.setName_vcust("XYZ");
        model4.setNum_vcust("9999999999");
        list.add(model4);
    }
}