package com.example.e_supermarket.customer.features.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.Common.Variables;
import com.example.e_supermarket.customer.PrefUtil;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.features.PlaceOrder.CustomerPayResponse;
import com.example.e_supermarket.customer.features.PlaceOrder.PlaceOrderResponse;
import com.example.e_supermarket.customer.features.adapters.Checkout_adapter;
import com.example.e_supermarket.customer.features.cartresponse.CartResponse;
import com.example.e_supermarket.customer.features.models.Checkout_model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceOrdFragment extends Fragment {


    private RecyclerView rv_placeord;
    private ArrayList<Checkout_model> list=new ArrayList<Checkout_model>();
    private Checkout_adapter mAdapter;
    private Toolbar tb_placeorder;
    private Button btn_plcord;
    private int flag=0;
    private String order_id="";
    private String amount="";

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
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new CartFragment());
                transaction.addToBackStack(null);
                transaction.commit();
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
                        flag=1;
                        meth_placeorder();

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

        setdata();


        return view;
    }

    private void meth_placeorder() {
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.placeorder(PlaceOrdFragment.this.getActivity().getIntent().getStringExtra("user_id")).enqueue(new Callback<PlaceOrderResponse>() {
            @Override
            public void onResponse(Call<PlaceOrderResponse> call, Response<PlaceOrderResponse> response) {
                //Toast.makeText(getActivity(), "Order Placed Successfully!", Toast.LENGTH_SHORT).show();
                //PlaceOrderFragment placeOrdFragment=response.body();
                PlaceOrderResponse placeOrderResponse=response.body();
                if (placeOrderResponse.getSuccess()==1)
                {
                    String amt= String.valueOf(placeOrderResponse.getAmt());
                    order_id=placeOrderResponse.getOrderId();

                    AlertDialog.Builder alert1=new AlertDialog.Builder(getActivity());
                    alert1.setTitle("Payment");
                    alert1.setMessage("Pay "+amt+" INR");
                    alert1.setPositiveButton("Pay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            met_custpay();

                            Toast.makeText(getActivity(), "Payment done"+order_id, Toast.LENGTH_SHORT).show();
                        }
                    }).create().show();
                }
            }

            @Override
            public void onFailure(Call<PlaceOrderResponse> call, Throwable t) {

            }
        });
    }

    private void met_custpay() {

        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);
        apiInterface
                .custPay(PrefUtil.getstringPref(Variables.userId,getActivity()).toString()
                        ,order_id.toString()).enqueue(new Callback<CustomerPayResponse>() {
            @Override
            public void onResponse(Call<CustomerPayResponse> call, Response<CustomerPayResponse> response) {
                CustomerPayResponse customerPayResponse=response.body();
                if (customerPayResponse.getSuccess()==1)
                {
                    Toast.makeText(getActivity(), "Payment Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerPayResponse> call, Throwable t) {

            }
        });

    }

    private void setdata()
    {

        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);

        apiInterface.getCart(getActivity().getIntent().getStringExtra("user_id")).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                mAdapter=new Checkout_adapter(PlaceOrdFragment.this,response.body().getSubarray());
                rv_placeord.setAdapter(mAdapter);
                rv_placeord.setLayoutManager(new LinearLayoutManager(getContext()));
                rv_placeord. addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {

            }
        });
    }
}