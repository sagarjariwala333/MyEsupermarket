package com.example.e_supermarket.customer.features.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.models.Cart_model;
import com.example.e_supermarket.customer.features.fragments.CartFragment;

import java.util.ArrayList;

public class Cart_adapter extends RecyclerView.Adapter<Cart_adapter.MyViewHolder>
{

    private final CartFragment Cartfragment;
    private final ArrayList<Cart_model> list;

    public Cart_adapter(CartFragment cartFragment, ArrayList<Cart_model> list)
    {
        Cartfragment=cartFragment;
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
        holder.iv_cart.setImageResource(list.get(position).getP_img());
        holder.tv_pid.setText(list.get(position).getP_id());
        holder.tv_pname.setText(list.get(position).getP_name());
        holder.tv_price.setText(list.get(position).getPrice());

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
