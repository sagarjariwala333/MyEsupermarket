package com.example.e_supermarket.customer.features.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.Common.Variables;
import com.example.e_supermarket.customer.PrefUtil;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.features.adapters.History_prod_adapter;
import com.example.e_supermarket.customer.features.models.History_prod_model;
import com.example.e_supermarket.customer.features.oldordprod.OldProductResponse;
import com.example.e_supermarket.customer.features.oldordprod.SubarrayItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History_prod_Fragment extends Fragment {


    private RecyclerView rv_his_prod;
    private Toolbar tb_his_prod;
    private ArrayList<History_prod_model> list=new ArrayList<History_prod_model>();
    private History_prod_adapter mAdapter;
    String order_id;

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

        order_id=getArguments().getString("order_id");
        setdata();


        return view;
    }


    private void setdata()
    {

        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);

        Toast.makeText(getActivity(), ""+PrefUtil.getstringPref(Variables.userId,History_prod_Fragment.this.getActivity())+order_id, Toast.LENGTH_SHORT).show();
       apiInterface.getOldProd(PrefUtil.getstringPref(Variables.userId,History_prod_Fragment.this.getActivity()),order_id)
             .enqueue(new Callback<OldProductResponse>() {
                 @Override
                 public void onResponse(Call<OldProductResponse> call, Response<OldProductResponse> response) {
                     mAdapter = new History_prod_adapter(History_prod_Fragment.this, response.body().getSubarray());
                     rv_his_prod.setAdapter(mAdapter);
                     rv_his_prod.setLayoutManager(new LinearLayoutManager(getContext()));
                     rv_his_prod.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                 }

                 @Override
                 public void onFailure(Call<OldProductResponse> call, Throwable t) {

                 }
             });
    }
}