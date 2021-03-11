package com.example.e_supermarket.customer.features.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.adapters.History_prod_adapter;
import com.example.e_supermarket.customer.features.models.History_prod_model;

import java.util.ArrayList;

public class History_prod_Fragment extends Fragment {


    private RecyclerView rv_his_prod;
    private Toolbar tb_his_prod;
    private ArrayList<History_prod_model> list=new ArrayList<History_prod_model>();
    private History_prod_adapter mAdapter;

    public History_prod_Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_history_prod, container, false);

        rv_his_prod=view.findViewById(R.id.rv_his_prod);
        tb_his_prod=view.findViewById(R.id.tb_his_prod);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_his_prod);

        setdata();
        setadapter();


        return view;
    }

    private void setadapter()
    {

        mAdapter=new History_prod_adapter(History_prod_Fragment.this,list);
        rv_his_prod.setAdapter(mAdapter);
        rv_his_prod.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_his_prod.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }

    private void setdata()
    {

        History_prod_model model1=new History_prod_model();
        model1.setHis_pimg(R.drawable.ic_launcher_background);
        model1.setHis_pid("P000001");
        model1.setHis_pname("Hide & Seek");
        model1.setHis_pprice("25");
        model1.setHis_pqut("5");
        list.add(model1);
    }
}