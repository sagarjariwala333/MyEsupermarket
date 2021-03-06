package com.example.e_supermarket.customer.staff.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.staff.adapters.StaffVordAdapter;
import com.example.e_supermarket.customer.staff.models.Vord_model;
import com.example.e_supermarket.customer.staff.staffpay.StaffpayResponse;
import com.example.e_supermarket.customer.staff.updatestaffpojo.UpdateFlagResponse;
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
    private Button btn_staff_pay;
    String cust_id = "";
    private String order_id;


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
        btn_staff_pay = view.findViewById(R.id.btn_staff_pay);


        cust_id = getArguments().getString("cust_id");
        order_id = getArguments().getString("order_id");
        meth_updateflag();
        rv_vord=view.findViewById(R.id.rv_vord);
        tv_view=view.findViewById(R.id.tv_view);




        btn_staff_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
                apiInterface.staffpay(cust_id,order_id).enqueue(new Callback<StaffpayResponse>() {
                    @Override
                    public void onResponse(Call<StaffpayResponse> call, Response<StaffpayResponse> response) {
                        StaffpayResponse staffpayResponse = response.body();
                        if(staffpayResponse.getSuccess4()==1)
                        {
                            FragmentManager manager=getActivity().getSupportFragmentManager();
                            FragmentTransaction transaction=manager.beginTransaction();
                            transaction.replace(R.id.fl_staff,new StaffVcustFragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
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

    private void meth_updateflag()
    {
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.updateFlag(cust_id,order_id).enqueue(new Callback<UpdateFlagResponse>() {
            @Override
            public void onResponse(Call<UpdateFlagResponse> call, Response<UpdateFlagResponse> response) {
                UpdateFlagResponse updateFlagResponse=response.body();
                if (updateFlagResponse.getSuccess()==1)
                {

                    Toast.makeText(getActivity(), ""+updateFlagResponse.getMeassage(), Toast.LENGTH_SHORT).show();
                    setdata();
                }
                else if (updateFlagResponse.getSuccess()==2)
                {
                    final androidx.appcompat.app.AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                    alert.setCancelable(false);
                    alert.setTitle("Alert");
                    alert.setMessage("You can`t see this. Already seen");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            FragmentManager manager=getActivity().getSupportFragmentManager();
                            FragmentTransaction transaction=manager.beginTransaction();
                            transaction.replace(R.id.fl_staff,new StaffVcustFragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    });
                    alert.create().show();


                }
                else
                {
                    Toast.makeText(getActivity(), ""+updateFlagResponse.getMeassage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateFlagResponse> call, Throwable t) {

                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setdata() {

        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.vieworder(cust_id,order_id).enqueue(new Callback<ViewOrderResponse>() {
            @Override
            public void onResponse(Call<ViewOrderResponse> call, Response<ViewOrderResponse> response)
            {
                Log.d("Customer",cust_id+order_id);
                Toast.makeText(getActivity(), ""+cust_id+order_id, Toast.LENGTH_SHORT).show();
                mAdapter=new StaffVordAdapter(StaffVordFragment.this,response.body().getSubarray());
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