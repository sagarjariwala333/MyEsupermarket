package com.example.e_supermarket.customer.features.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.fragments.WishlistFragment;
import com.example.e_supermarket.customer.features.models.Wishlist_model;

import java.util.ArrayList;

public class Wishlist_adapter extends RecyclerView.Adapter<Wishlist_adapter.MyViewHolder>
{

    private final WishlistFragment wishlistFragment;
    private final ArrayList<Wishlist_model> list;

    public Wishlist_adapter(WishlistFragment wishlistFragment, ArrayList<Wishlist_model> list)
    {
 
        this.wishlistFragment = wishlistFragment;
        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_wishlist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.iv_wish.setImageResource(list.get(position).getWish_pimg());
        holder.tv_wishid.setText(list.get(position).getWish_pid());
        holder.tv_wishname.setText(list.get(position).getWish_pname());
        holder.tv_wishprice.setText(list.get(position).getWish_pprice());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView iv_wish;
        private final TextView tv_wishid;
        private final TextView tv_wishname;
        private final TextView tv_wishprice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_wish=itemView.findViewById(R.id.iv_wish);
            tv_wishid=itemView.findViewById(R.id.tv_wishid);
            tv_wishname=itemView.findViewById(R.id.tv_wishname);
            tv_wishprice=itemView.findViewById(R.id.tv_wishprice);
        }
    }
}
