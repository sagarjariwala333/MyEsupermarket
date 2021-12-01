package com.example.e_supermarket.customer.staff.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.Common.Mob;
import com.example.e_supermarket.customer.Common.Variables;
import com.example.e_supermarket.customer.PrefUtil;
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
    private Toolbar tb_vcust;

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

        tb_vcust=view.findViewById(R.id.tb_vcust);

        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_vcust);
        setHasOptionsMenu(true);

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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        getActivity().getMenuInflater().inflate(R.menu.signout,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.tool_signout:
                PrefUtil.removeString(Variables.userId,getActivity());
                PrefUtil.removeString(Variables.role,getActivity());
                PrefUtil.removeBoolean(Variables.isLoggedIn,getActivity());
                PrefUtil.clearPreference(getActivity());
                Intent intent=new Intent(getActivity(), Mob.class);
                startActivity(intent);
        }
        return true;
    }
}