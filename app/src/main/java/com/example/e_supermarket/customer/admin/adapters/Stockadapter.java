package com.example.e_supermarket.customer.admin.adapters;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.admin.adminresponses.AddStockResponse;
import com.example.e_supermarket.customer.admin.adminresponses.RemoveStockResponse;
import com.example.e_supermarket.customer.admin.fragments.Fragment_stock;
import com.example.e_supermarket.customer.admin.viewprod.SubarrayItem;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Stockadapter extends RecyclerView.Adapter<Stockadapter.MyViewHolder> {
    private final Fragment_stock mActivity;
    private final List<SubarrayItem> list;

    public Stockadapter(Fragment_stock fragment_stock, List<SubarrayItem> list) {
        this.mActivity=fragment_stock;
        this.list=list;
    }

    @NonNull
    @Override
    public Stockadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_admin_stock,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Stockadapter.MyViewHolder holder, int position) {

        SubarrayItem item=list.get(position);

        holder.tv_stockid.setText(item.getProductId());
        holder.tv_stockqut.setText(item.getProductQuantity());
        holder.tv_stockname.setText(item.getProductName());

        Glide.with(mActivity.getActivity())
                .load(ApiCliet.ASSET_URL+item.getProductImg())
                .placeholder(R.drawable.ic_baseline_add)
                .into(holder.ib_stock);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            private Button btn_ar_remove;
            private Button btn_ar_add;
            private EditText et_ar_stock;
            private Button btn_ar_cancel;

            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(mActivity.getActivity());
                dialog.setContentView(R.layout.addremovestock);
                dialog.setCancelable(true);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations= android.R.style.Animation_Dialog;

                et_ar_stock=dialog.findViewById(R.id.et_ar_stock);
                btn_ar_cancel=dialog.findViewById(R.id.btn_ar_cancel);
                btn_ar_add=dialog.findViewById(R.id.btn_ar_add);
                btn_ar_remove=dialog.findViewById(R.id.btn_ar_remove);

                btn_ar_cancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                        dialog.dismiss();
                    }
                });

                btn_ar_remove.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(et_ar_stock.getText().toString().trim())) {
                            et_ar_stock.setError("Enter stock");
                        } else {
                            ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
                            apiInterface.removestock(holder.tv_stockid.getText().toString(), et_ar_stock.getText().toString())
                                    .enqueue(new Callback<RemoveStockResponse>() {
                                        @Override
                                        public void onResponse(Call<RemoveStockResponse> call, Response<RemoveStockResponse> response) {
                                            if (response.isSuccessful() && response.body() != null) {
                                                RemoveStockResponse removeStockResponse = response.body();
                                                if (removeStockResponse.getSuccess() == 1) {
                                                    Toast.makeText(mActivity.getActivity(), "" + removeStockResponse.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                                    FragmentManager manager=mActivity.getActivity().getSupportFragmentManager();
                                                    FragmentTransaction transaction=manager.beginTransaction();
                                                    transaction.replace(R.id.fl_cust,new Fragment_stock());
                                                    transaction.addToBackStack(null);
                                                    transaction.commit();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<RemoveStockResponse> call, Throwable t) {

                                        }
                                    });
                            Toast.makeText(mActivity.getActivity(), "Removed", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }

                });

                btn_ar_add.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(et_ar_stock.getText().toString().trim())) {
                            et_ar_stock.setError("Enter stock");
                        } else {
                            ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);
                            apiInterface.addstock(holder.tv_stockid.getText().toString(), et_ar_stock.getText().toString())
                                    .enqueue(new Callback<AddStockResponse>() {
                                        @Override
                                        public void onResponse(Call<AddStockResponse> call, Response<AddStockResponse> response) {
                                            if (response.isSuccessful() && response.body() != null) {
                                                AddStockResponse addStockResponse = response.body();
                                                if (addStockResponse.getSuccess() == 1) {
                                                    Toast.makeText(mActivity.getActivity(), "" + addStockResponse.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                                    FragmentManager manager=mActivity.getActivity().getSupportFragmentManager();
                                                    FragmentTransaction transaction=manager.beginTransaction();
                                                    transaction.replace(R.id.fl_cust,new Fragment_stock());
                                                    transaction.addToBackStack(null);
                                                    transaction.commit();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<AddStockResponse> call, Throwable t) {

                                        }

                                    });

                            Toast.makeText(mActivity.getActivity(), "Added", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView ib_stock;
        public TextView tv_stockid;
        public TextView tv_stockname;
        public TextView tv_stockqut;
        public EditText et_disstock;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            ib_stock = itemView.findViewById(R.id.ib_stock);
            tv_stockid = itemView.findViewById(R.id.tv_stockid);
            tv_stockname = itemView.findViewById(R.id.tv_stockname);
            tv_stockqut = itemView.findViewById(R.id.tv_stockqut);

        }
    }
}
