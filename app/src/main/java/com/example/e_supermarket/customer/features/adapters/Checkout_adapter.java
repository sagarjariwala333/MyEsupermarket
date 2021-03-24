package com.example.e_supermarket.customer.features.adapters;

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
import com.example.e_supermarket.customer.features.cartresponse.SubarrayItem;
import com.example.e_supermarket.customer.features.fragments.PlaceOrdFragment;

import java.util.List;

public class Checkout_adapter extends RecyclerView.Adapter<Checkout_adapter.MyViewHolder>
{


    private final PlaceOrdFragment placeOrdFragment;
    private List<SubarrayItem> list;


    public Checkout_adapter(PlaceOrdFragment placeOrdFragment, List<SubarrayItem> subarray) {
        this.placeOrdFragment=placeOrdFragment;
        this.list=subarray;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_placeorder,parent,false);
        return new Checkout_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {

        SubarrayItem item = list.get(position);
        holder.tv_chkordid.setText(item.getProductId());
        holder.tv_chkordname.setText(item.getProductName());
        holder.tv_chkordprice.setText(item.getProductPrice());
        holder.tv_chkqut.setText(item.getProductQuantity());
        Glide.with(placeOrdFragment.getActivity()).load(ApiCliet.ASSET_URL+item.getProductImg()).placeholder(R.drawable.ic_baseline_add).into(holder.iv_chkord);

    }

    @Override
    public int getItemCount()
    {
            return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_chkord;
        private final TextView tv_chkordid;
        private final TextView tv_chkordname;
        private final TextView tv_chkordprice;
        private final TextView tv_chkqut;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_chkord=itemView.findViewById(R.id.iv_chkord);
            tv_chkordid=itemView.findViewById(R.id.tv_chkordid);
            tv_chkordname=itemView.findViewById(R.id.tv_chkordname);
            tv_chkordprice=itemView.findViewById(R.id.tv_chkordprice);
            tv_chkqut=itemView.findViewById(R.id.tv_chkqut);
        }
    }
}
