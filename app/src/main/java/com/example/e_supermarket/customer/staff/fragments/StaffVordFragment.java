package com.example.e_supermarket.customer.staff.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.staff.adapters.StaffVordAdapter;
import com.example.e_supermarket.customer.staff.models.Vord_model;
import com.example.e_supermarket.customer.staff.staffpay.StaffpayResponse;
import com.example.e_supermarket.customer.staff.vieword_response.ViewOrderResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StaffVordFragment extends Fragment {


    private RecyclerView rv_vord;
    private ArrayList<Vord_model> list;
    private StaffVordAdapter mAdapter;
    private TextView tv_view;
    private Button btn_plcord;
    String cust_id = "";


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
        btn_plcord = view.findViewById(R.id.btn_plcord);

        cust_id = getArguments().getString("cust_id");

        rv_vord=view.findViewById(R.id.rv_vord);
        tv_view=view.findViewById(R.id.tv_view);


        setdata();

        btn_plcord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
                apiInterface.staffpay(cust_id).enqueue(new Callback<StaffpayResponse>() {
                    @Override
                    public void onResponse(Call<StaffpayResponse> call, Response<StaffpayResponse> response) {
                        StaffpayResponse staffpayResponse = response.body();
                        if(staffpayResponse.getSuccess4()==1) {
                            Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StaffpayResponse> call, Throwable t) {

                    }
                });
            }
        });
        return view;
    }

    private void setdata() {

        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.vieworder(StaffVordFragment.this.getActivity().getIntent().getStringExtra("user_id")).enqueue(new Callback<ViewOrderResponse>() {
            @Override
            public void onResponse(Call<ViewOrderResponse> call, Response<ViewOrderResponse> response) {
                mAdapter=new StaffVordAdapter(StaffVordFragment.this,response.body().getSubarr());
                rv_vord.setAdapter(mAdapter);
                rv_vord.setLayoutManager(new LinearLayoutManager(getContext()));
                rv_vord.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            }

            @Override
            public void onFailure(Call<ViewOrderResponse> call, Throwable t) {

            }
        });

    }
}