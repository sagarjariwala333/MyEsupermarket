package com.example.e_supermarket.customer.admin.adapters;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.admin.fragments.UpdateProfileFragment;
import com.example.e_supermarket.customer.admin.fragments.productfragment;
import com.example.e_supermarket.customer.admin.models.productmodel;

import java.util.ArrayList;

public class productadpter extends RecyclerView.Adapter<productadpter.MyViewHolder> {
    private final productfragment mActivity;
    private final ArrayList<productmodel> list;

    public productadpter(productfragment productfragment, ArrayList<productmodel> list) {
        this.mActivity=productfragment;
        this.list=list;
    }

    @NonNull
    @Override
    public productadpter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_admin_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productadpter.MyViewHolder holder, int position)
    {

        holder.iv_product.setImageResource(list.get(position).getImage());
        holder.tv_productid.setText(list.get(position).getId());
        holder.tv_productname.setText(list.get(position).getName());
        holder.tv_price.setText(list.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert=new AlertDialog.Builder(mActivity.getActivity());
                alert.setTitle("Product Id :- ");
                alert.setMessage("Product name :- ");
                alert.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //alert.setCancelable(true);
                    }
                });

                alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        FragmentManager manager=mActivity.getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction=manager.beginTransaction();
                        transaction.replace(R.id.frame,new UpdateProfileFragment());
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

    public class MyViewHolder extends RecyclerView.ViewHolder
    {


        public ImageButton iv_product;
        public TextView tv_productid;
        public TextView tv_productname;
        public TextView tv_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_product = itemView.findViewById(R.id.iv_product);
            tv_productid = itemView.findViewById(R.id.tv_productid);
            tv_productname = itemView.findViewById(R.id.tv_productname);
            tv_price = itemView.findViewById(R.id.tv_price);

        }
    }
}
