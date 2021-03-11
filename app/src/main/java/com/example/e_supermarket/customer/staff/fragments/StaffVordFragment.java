package com.example.e_supermarket.customer.staff.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.staff.adapters.StaffVordAdapter;
import com.example.e_supermarket.customer.staff.models.Vord_model;

import java.util.ArrayList;


public class StaffVordFragment extends Fragment {


    private RecyclerView rv_vord;
    private ArrayList<Vord_model> list;
    private StaffVordAdapter mAdapter;
    private TextView tv_view;


    public StaffVordFragment() {
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
        View view = inflater.inflate(R.layout.fragment_staff_vord, container, false);



        rv_vord=view.findViewById(R.id.rv_vord);
        tv_view=view.findViewById(R.id.tv_view);


        setdata();
        setadapter();


        return view;
    }

    private void setadapter() {

        mAdapter=new StaffVordAdapter(StaffVordFragment.this,list);
        rv_vord.setAdapter(mAdapter);
        rv_vord.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_vord.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

    }

    private void setdata() {


        list=new ArrayList<>();

        Vord_model model1 = new Vord_model();
        model1.setImg_vord(R.drawable.ic_launcher_background);
        model1.setId_vord("P000001");
        model1.setName_vord("Hide & Seek");
        model1.setType_vord("Biscuit");
        model1.setQut_vord("Q-005");
        list.add(model1);

        Vord_model model2 = new Vord_model();
        model2.setImg_vord(R.drawable.ic_launcher_background);
        model2.setId_vord("P000001");
        model2.setName_vord("Dairy Milk");
        model2.setType_vord("Chocolate");
        model2.setQut_vord("Q-020");
        list.add(model2);

        Vord_model model3 = new Vord_model();
        model3.setImg_vord(R.drawable.ic_launcher_background);
        model3.setId_vord("P000003");
        model3.setName_vord("Good day");
        model3.setType_vord("Biscuit");
        model3.setQut_vord("Q-010");
        list.add(model3);

        Vord_model model4 = new Vord_model();
        model4.setImg_vord(R.drawable.ic_launcher_background);
        model4.setId_vord("P000004");
        model4.setName_vord("Dark Fantacy");
        model4.setType_vord("Biscuit");
        model4.setQut_vord("Q-015");
        list.add(model4);

    }
}