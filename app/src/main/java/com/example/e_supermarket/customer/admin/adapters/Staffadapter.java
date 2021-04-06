 package com.example.e_supermarket.customer.admin.adapters;

 import android.app.ProgressDialog;
 import android.content.DialogInterface;
import android.os.Bundle;
 import android.os.Handler;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.admin.fragments.UpdateProfileFragment;
 import com.example.e_supermarket.customer.admin.fragments.fragment_Staff;
 import com.example.e_supermarket.customer.admin.viewstaff.SubarrayItem;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.features.customerresponse.RemoveStaffResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class Staffadapter extends RecyclerView.Adapter<Staffadapter.MyViewHolder> {


     private final FragmentActivity mActivity;
     private final List<SubarrayItem> list;

     public Staffadapter(FragmentActivity activity, List<SubarrayItem> subarray) {
         this.mActivity=activity;
         this.list=subarray;
     }

     @NonNull
    @Override
    public Staffadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_admin_staff,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Staffadapter.MyViewHolder holder, int position)
    {
        SubarrayItem subarrayItem=list.get(position);
        holder.tv_staffid.setText(subarrayItem.getUserId());
        holder.tv_staffname.setText(subarrayItem.getFirstName());
        Glide.with(mActivity).load(ApiCliet.ASSET_URL+subarrayItem.getIdPhoto()).placeholder(R.drawable.ic_baseline_add).into(holder.iv_staff);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final AlertDialog.Builder alert=new AlertDialog.Builder(mActivity);
                alert.setTitle("Staff-ID :- "+holder.tv_staffid.getText().toString());
                alert.setMessage("Staff Name :- "+holder.tv_staffname.getText().toString());

                alert.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);
                        apiInterface.removeStaff(holder.tv_staffid.getText().toString()).enqueue(new Callback<RemoveStaffResponse>() {
                            @Override
                            public void onResponse(Call<RemoveStaffResponse> call, Response<RemoveStaffResponse> response)
                            {
                                if (response.isSuccessful() && response.body()!=null)
                                {
                                    RemoveStaffResponse removeStaffResponse=response.body();
                                    if (removeStaffResponse.getSuccess()==1)
                                    {
                                        FragmentManager manager=mActivity.getSupportFragmentManager();
                                        FragmentTransaction transaction=manager.beginTransaction();
                                        transaction.replace(R.id.frame,new fragment_Staff());
                                        transaction.addToBackStack(null);
                                        transaction.commit();

                                        ProgressDialog progressDialog=new ProgressDialog(mActivity);
                                        progressDialog.setMessage("Please Wait");
                                        progressDialog.show();

                                        Runnable progressRunnable = new Runnable() {

                                            @Override
                                            public void run() {
                                                progressDialog.cancel();
                                            }
                                        };

                                        Handler pdCanceller = new Handler();
                                        pdCanceller.postDelayed(progressRunnable, 1000);
                                        Toast.makeText(mActivity, ""+removeStaffResponse.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(mActivity, ""+removeStaffResponse.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<RemoveStaffResponse> call, Throwable t) {

                            }
                        });

                    }
                });

                alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        UpdateProfileFragment updateProfileFragment= new UpdateProfileFragment();
                        Bundle bundle=new Bundle();
                        bundle.putString("staff_id",subarrayItem.getUserId());
                        updateProfileFragment.setArguments(bundle);
                        FragmentManager manager=mActivity.getSupportFragmentManager();
                        FragmentTransaction transaction=manager.beginTransaction();
                        transaction.replace(R.id.frame,updateProfileFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.setCancelable(true);
                    }
                });
                alert.create().show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_staff;
        private final TextView tv_staffid;
        private final TextView tv_staffname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_staff=itemView.findViewById(R.id.iv_staff);
            tv_staffid=itemView.findViewById(R.id.tv_staffid);
            tv_staffname=itemView.findViewById(R.id.tv_staffname);
        }

    }
}
