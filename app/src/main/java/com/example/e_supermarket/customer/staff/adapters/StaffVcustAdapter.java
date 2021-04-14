package com.example.e_supermarket.customer.staff.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.staff.fragments.StaffVcustFragment;
import com.example.e_supermarket.customer.staff.fragments.StaffVordFragment;
import com.example.e_supermarket.customer.staff.viewcustomerresponce.SubarrayItem;

import java.util.List;

public class StaffVcustAdapter extends RecyclerView.Adapter<StaffVcustAdapter.MyViewHolder> {
    private final StaffVcustFragment vcustFragment;
    private final List<SubarrayItem> list;
    //String cid;
    SubarrayItem item;

    public StaffVcustAdapter(StaffVcustFragment staffVcustFragment, List<SubarrayItem> list)
    {
        this.vcustFragment=staffVcustFragment;
        this.list=list;
    }

    @NonNull
    @Override
    public StaffVcustAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_vcust,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffVcustAdapter.MyViewHolder holder, int position)
    {
        item = list.get(position);
        holder.tv_vcustid.setText(item.getUserId());
        holder.tv_vcustname.setText(item.getFirstName());
        holder.tv_vcustnum.setText(item.getMobileNo());
        //String a=item.getFlag();
        holder.cid=item.getUserId();
        holder.oid=item.getOrderId();
        Glide.with(vcustFragment.getActivity()).load(ApiCliet.ASSET_URL+item.getIdPhoto()).into(holder.iv_vcust);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vcustFragment.getActivity(), ""+holder.cid+holder.oid, Toast.LENGTH_SHORT).show();
                //Toast.makeText(vcustFragment.getActivity(), ""+holder.oid, Toast.LENGTH_SHORT).show();
                Toast.makeText(vcustFragment.getActivity(), "Done", Toast.LENGTH_SHORT).show();
                StaffVordFragment staffVordFragment = new StaffVordFragment();
                Bundle bundle = new Bundle();
                bundle.putString("cust_id", holder.cid);
                bundle.putString("order_id", holder.oid);
                staffVordFragment.setArguments(bundle);
                FragmentManager manager=vcustFragment.getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_staff,staffVordFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_vcust;
        private final TextView tv_vcustid;
        private final TextView tv_vcustname;
        private final TextView tv_vcustnum;
        private final TextView tv_view;
        private String cid;
        private String oid;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            iv_vcust=itemView.findViewById(R.id.iv_vcust);
            tv_vcustid=itemView.findViewById(R.id.tv_vcustid);
            tv_vcustname=itemView.findViewById(R.id.tv_vcustname);
            tv_vcustnum=itemView.findViewById(R.id.tv_vcustnum);
            tv_view=itemView.findViewById(R.id.tv_view);

          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tv_view.setBackgroundColor(Color.RED);
                    //tv_view.setBackground(Color.RED);
                    StaffVordFragment staffVordFragment = new StaffVordFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("cust_id", item.getUserId());
                    bundle.putString("order_id", item.getOrderId());
                    staffVordFragment.setArguments(bundle);
                    FragmentManager manager=vcustFragment.getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.replace(R.id.fl_staff,staffVordFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });*/
        }

    }
}
