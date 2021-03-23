package com.example.e_supermarket.customer.features.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.features.cartresponse.SubarrayItem;
import com.example.e_supermarket.customer.features.fragments.CartFragment;

import java.util.List;

public class Cart_adapter extends RecyclerView.Adapter<Cart_adapter.MyViewHolder>
{

    private final CartFragment cartfragment;
    private final List<SubarrayItem> list;

    public Cart_adapter(CartFragment cartFragment, List<SubarrayItem> list)
    {
        this.cartfragment=cartFragment;
        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.onerow_cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        SubarrayItem item=list.get(position);
        holder.tv_pid.setText(item.getProductId());
        holder.tv_pname.setText(item.getProductName());
        holder.tv_price.setText(item.getProductPrice());
        holder.et_disstock.setText(item.getProductQuantity());
        Glide.with(cartfragment.getActivity())
                .load(ApiCliet.ASSET_URL+item.getProductImg())
                .placeholder(R.drawable.ic_baseline_add)
                .into(holder.iv_cart);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_cart;
        private final TextView tv_pid;
        private final TextView tv_pname;
        private final TextView tv_price;
        private final ImageButton ib_add;
        private final ImageButton ib_minus;
        private final EditText et_disstock;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cart=itemView.findViewById(R.id.iv_cart);
            tv_pid=itemView.findViewById(R.id.tv_pid);
            tv_pname=itemView.findViewById(R.id.tv_pname);
            tv_price=itemView.findViewById(R.id.tv_price);
            ib_add=itemView.findViewById(R.id.ib_add);
            ib_minus=itemView.findViewById(R.id.ib_minus);
            et_disstock=itemView.findViewById(R.id.et_disstock);

            ib_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    int num= Integer.parseInt(et_disstock.getText().toString());
                    num=num+1;
                    String str= String.valueOf(num);
                    et_disstock.setText(str);
                }
            });

            ib_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    int num= Integer.parseInt(et_disstock.getText().toString());
                    if (num!=0)
                    {
                        num = num - 1;
                        String str = String.valueOf(num);
                        et_disstock.setText(str);
                    }
                }
            });
        }
    }
}