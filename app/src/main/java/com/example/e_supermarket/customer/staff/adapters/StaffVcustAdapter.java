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

    public StaffVcustAdapter(StaffVcustFragment staffVcustFragment, List<SubarrayItem> list) {
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


        SubarrayItem item = list.get(position);
        holder.tv_vcustid.setText(item.getUserId());
        holder.tv_vcustname.setText(item.getFirstName());
        holder.tv_vcustnum.setText(item.getMobileNo());
        Glide.with(vcustFragment.getActivity()).load(ApiCliet.ASSET_URL+item.getIdPhoto()).into(holder.iv_vcust);

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
