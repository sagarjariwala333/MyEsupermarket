package com.example.e_supermarket.customer.features.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.adapters.Checkout_adapter;
import com.example.e_supermarket.customer.features.models.Checkout_model;

import java.util.ArrayList;

public class PlaceOrdFragment extends Fragment {


    private RecyclerView rv_placeord;
    private ArrayList<Checkout_model> list=new ArrayList<Checkout_model>();
    private Checkout_adapter mAdapter;
    private Toolbar tb_placeorder;
    private Button btn_plcord;

    public PlaceOrdFragment() {
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
        View view=inflater.inflate(R.layout.fragment_placeord, container, false);
        rv_placeord=view.findViewById(R.id.rv_placeord);
        tb_placeorder=view.findViewById(R.id.tb_placeorder);
        btn_plcord=view.findViewById(R.id.btn_plcord);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_placeorder);

        tb_placeorder.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().onBackPressed();
            }
        });

        btn_plcord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                alert.setTitle("Place Order");
                alert.setMessage("You can`t undo this operation");
                alert.setPositiveButton("Place Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ProgressDialog progressDialog=new ProgressDialog(getActivity());
                        progressDialog.setTitle("Please Wait");
                        progressDialog.setMessage("Loading");
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
                            }
                        }).start();
                        progressDialog.show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.create().show();
            }
        });

        setadapter();
        setdata();


        return view;
    }

    private void setdata()
    {
        Checkout_model model1=new Checkout_model();
        model1.setImg_chord(R.drawable.ic_launcher_background);
        model1.setChkord_id("P00001");
        model1.setChkord_name("Hide & Seek");
        model1.setChkord_price("50");
        model1.setChkord_qut("5");
        list.add(model1);

        Checkout_model model2=new Checkout_model();
        model2.setImg_chord(R.drawable.ic_launcher_background);
        model2.setChkord_id("P00002");
        model2.setChkord_name("Dark Fantacy");
        model2.setChkord_price("50");
        model1.setChkord_qut("5");
        list.add(model2);

        Checkout_model model3=new Checkout_model();
        model3.setImg_chord(R.drawable.ic_launcher_background);
        model3.setChkord_id("P00003");
        model3.setChkord_name("Dairy Milk");
        model3.setChkord_price("20");
        model1.setChkord_qut("5");
        list.add(model3);

    }

    private void setadapter()
    {
        mAdapter=new Checkout_adapter(PlaceOrdFragment.this,list);
        rv_placeord.setAdapter(mAdapter);
        rv_placeord.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_placeord.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }
}