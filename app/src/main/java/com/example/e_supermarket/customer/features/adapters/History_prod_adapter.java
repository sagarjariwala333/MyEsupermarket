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
import com.example.e_supermarket.customer.features.fragments.History_prod_Fragment;
import com.example.e_supermarket.customer.features.oldordprod.SubarrayItem;

import java.util.List;

public class History_prod_adapter extends RecyclerView.Adapter<History_prod_adapter.MyViewHolder>
{


    private final History_prod_Fragment history_prod_fragment;
    private final List<SubarrayItem> list;

    public History_prod_adapter(History_prod_Fragment history_prod_fragment, List<SubarrayItem> subarray) {
        this.history_prod_fragment=history_prod_fragment;
        this.list=subarray;
    }

    @NonNull
    @Override
    public History_prod_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_history_prod,parent,false);
        return new History_prod_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull History_prod_adapter.MyViewHolder holder, int position)
    {

        SubarrayItem item=list.get(position);
        holder.tv_hisprodid.setText(item.getProductId());
        holder.tv_hispordname.setText(item.getProductName());
        holder.tv_hisqut.setText(item.getProductQuantity());
        holder.tv_hispordprice.setText(item.getProductPrice());

        Glide.with(history_prod_fragment.getActivity()).load(ApiCliet.ASSET_URL+item.getProductImg())
                .placeholder(R.drawable.ic_baseline_add)
                .into(holder.iv_his);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView iv_his;
        private final TextView tv_hisprodid;
        private final TextView tv_hispordname;
        private final TextView tv_hispordprice;
        private final TextView tv_hisqut;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            iv_his=itemView.findViewById(R.id.iv_his);
            tv_hisprodid=itemView.findViewById(R.id.tv_hisprodid);
            tv_hispordname=itemView.findViewById(R.id.tv_hispordname);
            tv_hispordprice=itemView.findViewById(R.id.tv_hispordprice);
            tv_hisqut=itemView.findViewById(R.id.tv_hisqut);
        }
    }
}
