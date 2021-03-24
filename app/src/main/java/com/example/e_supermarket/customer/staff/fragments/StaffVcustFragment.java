package com.example.e_supermarket.customer.staff.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.staff.adapters.StaffVcustAdapter;
import com.example.e_supermarket.customer.staff.models.Vcust_model;
import com.example.e_supermarket.customer.staff.viewcustomerresponce.VcustResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        return view;
    }


    private void setdata()
    {
        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.vcust().enqueue(new Callback<VcustResponse>() {
            @Override
            public void onResponse(Call<VcustResponse> call, Response<VcustResponse> response) {

                mAdapter=new StaffVcustAdapter(StaffVcustFragment.this,response.body().getSubarray());
                rv_vcust.setAdapter(mAdapter);
                rv_vcust.setLayoutManager(new LinearLayoutManager(getContext()));
                //  if (response.isSuccessful() && response.body()!=null)
            }

            @Override
            public void onFailure(Call<VcustResponse> call, Throwable t) {

            }
        });
    }
}