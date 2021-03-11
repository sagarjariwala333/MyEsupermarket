package com.example.e_supermarket.customer.staff.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.staff.fragments.StaffVordFragment;
import com.example.e_supermarket.customer.staff.models.Vord_model;

import java.util.ArrayList;

public class StaffVordAdapter extends RecyclerView.Adapter<StaffVordAdapter.MyViewHolder> {
    private final StaffVordFragment staffVordFragment;
    private final ArrayList<Vord_model> list;

    public StaffVordAdapter(StaffVordFragment staffVordFragment, ArrayList<Vord_model> list) {
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


        holder.iv_vord.setImageResource(list.get(position).getImg_vord());
        holder.tv_vordid.setText(list.get(position).getId_vord());
        holder.tv_vordname.setText(list.get(position).getName_vord());
        holder.tv_vordtype.setText(list.get(position).getType_vord());
        holder.tv_vordqut.setText(list.get(position).getQut_vord());

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
