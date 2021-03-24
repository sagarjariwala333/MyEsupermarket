package com.example.e_supermarket.customer.staff.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.staff.fragments.StaffVordFragment;
import com.example.e_supermarket.customer.staff.models.Vord_model;
import com.example.e_supermarket.customer.staff.vieword_response.SubarrItem;

import java.util.ArrayList;
import java.util.List;

public class StaffVordAdapter extends RecyclerView.Adapter<StaffVordAdapter.MyViewHolder> {
    private final StaffVordFragment staffVordFragment;
    private final List<SubarrItem> list;

    public StaffVordAdapter(StaffVordFragment staffVordFragment, List<SubarrItem> list) {
        this.staffVordFragment=staffVordFragment;
        this.list=list;
    }

    @NonNull
    @Override
    public StaffVordAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_vord,parent,false);
        return new StaffVordAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffVordAdapter.MyViewHolder holder, int position) {
        SubarrItem subarrItem=list.get(position);
        holder.tv_vordid.setText(subarrItem.getProductId());
        holder.tv_vordname.setText(subarrItem.getProductName());
        holder.tv_vordqut.setText(subarrItem.getProductQuantity());
        holder.tv_vordtype.setText(subarrItem.getProductType());
        Glide.with(staffVordFragment.getActivity()).load(ApiCliet.ASSET_URL+subarrItem.getProductImg()).into(holder.iv_vord);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        private final ImageView iv_vord;
        private final TextView tv_vordid;
        private final TextView tv_vordname;
        private final TextView tv_vordtype;
        private final TextView tv_vordqut;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);


            iv_vord=itemView.findViewById(R.id.iv_vord);
            tv_vordid=itemView.findViewById(R.id.tv_vordid);
            tv_vordname=itemView.findViewById(R.id.tv_vordname);
            tv_vordtype=itemView.findViewById(R.id.tv_vordtype);
            tv_vordqut=itemView.findViewById(R.id.tv_vordqut);

        }
    }
}
