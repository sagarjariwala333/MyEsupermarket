package com.example.e_supermarket.customer.features.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.fragments.CartFragment;
import com.example.e_supermarket.customer.features.fragments.PlaceOrdFragment;
import com.example.e_supermarket.customer.features.models.Checkout_model;

import java.util.ArrayList;

public class Checkout_adapter extends RecyclerView.Adapter<Checkout_adapter.MyViewHolder>
{

    private final PlaceOrdFragment PlaceOrderFragment;
    private final ArrayList<Checkout_model> list;

    public Checkout_adapter(PlaceOrdFragment placeOrderFragment, ArrayList<Checkout_model> list) {
        this.PlaceOrderFragment=placeOrderFragment;
        this.list=list;
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
        holder.iv_chkord.setImageResource(list.get(position).getImg_chkord());
        holder.tv_chkordid.setText(list.get(position).getChkord_id());
        holder.tv_chkordname.setText(list.get(position).getChkord_name());
        holder.tv_chkordprice.setText(list.get(position).getChkord_price());
        holder.tv_chkqut.setText(list.get(position).getChkord_qut());
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
