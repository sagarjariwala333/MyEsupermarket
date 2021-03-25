package com.example.e_supermarket.customer.features.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.fragments.HistoryFrgament;
import com.example.e_supermarket.customer.features.fragments.History_prod_Fragment;
import com.example.e_supermarket.customer.features.oldorders.SubarrayItem;

import java.util.List;

public class History_adapter extends RecyclerView.Adapter<History_adapter.MyViewHolder> {

    private final HistoryFrgament historyFrgament;
    private final List<SubarrayItem> list;


    public History_adapter(HistoryFrgament historyFrgament, List<SubarrayItem> subarray)
    {
        this.historyFrgament=historyFrgament;
        this.list=subarray;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_history, parent, false);
        return new History_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SubarrayItem item = list.get(position);
        //holder.iv_bill.setImageResource(list.get(position).getBill_img());
        holder.tv_billid.setText(item.getOldOrderid());
        holder.tv_billdate.setText(item.getDateOldorders());
        holder.tv_billamt.setText("7000");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                History_prod_Fragment history_prod_fragment = new History_prod_Fragment();
                bundle.putString("order_id", item.getOrderId());
                history_prod_fragment.setArguments(bundle);
                FragmentManager manager = historyFrgament.getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fl_cust, history_prod_fragment);
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
        //private final ImageView iv_bill;
        private final TextView tv_billid;
        private final TextView tv_billdate;
        private final TextView tv_billamt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // iv_bill=itemView.findViewById(R.id.iv_bill);
            tv_billid = itemView.findViewById(R.id.tv_billid);
            tv_billdate = itemView.findViewById(R.id.tv_billdate);
            tv_billamt = itemView.findViewById(R.id.tv_billamt);
        }

    }
}
