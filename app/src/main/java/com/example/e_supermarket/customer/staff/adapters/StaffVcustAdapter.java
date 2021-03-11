package com.example.e_supermarket.customer.staff.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.staff.fragments.StaffVcustFragment;
import com.example.e_supermarket.customer.staff.fragments.StaffVordFragment;
import com.example.e_supermarket.customer.staff.models.Vcust_model;

import java.util.ArrayList;

public class StaffVcustAdapter extends RecyclerView.Adapter<StaffVcustAdapter.MyViewHolder> {
    private final StaffVcustFragment vcustFragment;
    private final ArrayList<Vcust_model> list;

    public StaffVcustAdapter(StaffVcustFragment staffVcustFragment, ArrayList<Vcust_model> list) {
        this.vcustFragment=staffVcustFragment;
        this.list=list;
    }

    @NonNull
    @Override
    public StaffVcustAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_vcust,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffVcustAdapter.MyViewHolder holder, int position)
    {


        holder.iv_vcust.setImageResource(list.get(position).getImg_vcust());
        holder.tv_vcustid.setText(list.get(position).getId_vcust());
        holder.tv_vcustname.setText(list.get(position).getName_vcust());
        holder.tv_vcustnum.setText(list.get(position).getNum_vcust());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(vcustFragment.getActivity(), "rfv", Toast.LENGTH_SHORT).show();
                FragmentManager manager=vcustFragment.getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_staff,new StaffVordFragment());
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

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            iv_vcust=itemView.findViewById(R.id.iv_vcust);
            tv_vcustid=itemView.findViewById(R.id.tv_vcustid);
            tv_vcustname=itemView.findViewById(R.id.tv_vcustname);
            tv_vcustnum=itemView.findViewById(R.id.tv_vcustnum);
        }
    }
}
