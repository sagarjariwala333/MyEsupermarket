package com.example.e_supermarket.customer.temp;

import android.app.Activity;
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

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Activity activity;
    private List<SubarrayItem> list;

    public RecyclerAdapter(Activity activity, List<SubarrayItem> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_vcust,parent,false);
        return new RecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position)
    {
        SubarrayItem item = list.get(position);
        //holder.iv_vcust.setImageResource(list.get(position).getImg_vcust());
        //holder.tv_vcustid.setText(list.get(position).getId_vcust());
        Glide.with(activity).load(ApiCliet.ASSET_URL + item.getIdPhoto()).placeholder(R.mipmap.ic_launcher).into(holder.iv_vcust);
        holder.tv_vcustname.setText(item.getFirstName());
        holder.tv_vcustnum.setText(item.getMobileNo());
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
