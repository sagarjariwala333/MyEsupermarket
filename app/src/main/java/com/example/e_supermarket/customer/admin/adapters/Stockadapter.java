package com.example.e_supermarket.customer.admin.adapters;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.admin.fragments.Fragment_stock;
import com.example.e_supermarket.customer.admin.models.stockmodel;

import java.util.ArrayList;

public class Stockadapter extends RecyclerView.Adapter<Stockadapter.MyViewHolder> {
    private final Fragment_stock mActivity;
    private final ArrayList<stockmodel> list;

    public Stockadapter(Fragment_stock fragment_stock, ArrayList<stockmodel> list) {
        this.mActivity=fragment_stock;
        this.list=list;
    }

    @NonNull
    @Override
    public Stockadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_admin_stock,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Stockadapter.MyViewHolder holder, int position) {


        holder.ib_stock.setImageResource(list.get(position).getStock_Image());
        holder.tv_stockid.setText(list.get(position).getStock_qut());
        holder.tv_stockname.setText(list.get(position).getStock_Name());
        holder.tv_stockqut.setText(list.get(position).getStock_qut());
        // holder.et_disstock.setText(list.get(position).getStock_set());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            private Button btn_ar_remove;
            private Button btn_ar_add;
            private EditText et_ar_stock;
            private Button btn_ar_cancel;

            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(mActivity.getActivity());
                dialog.setContentView(R.layout.addremovestock);
                dialog.setCancelable(true);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations= android.R.style.Animation_Dialog;

                et_ar_stock=dialog.findViewById(R.id.et_ar_stock);
                btn_ar_cancel=dialog.findViewById(R.id.btn_ar_cancel);
                btn_ar_add=dialog.findViewById(R.id.btn_ar_add);
                btn_ar_remove=dialog.findViewById(R.id.btn_ar_remove);

                btn_ar_cancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btn_ar_remove.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mActivity.getActivity(), "Removed", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                btn_ar_add.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mActivity.getActivity(), "Added", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageButton ib_stock;
        public TextView tv_stockid;
        public TextView tv_stockname;
        public TextView tv_stockqut;
        public EditText et_disstock;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            ib_stock = itemView.findViewById(R.id.ib_stock);
            tv_stockid = itemView.findViewById(R.id.tv_stockid);
            tv_stockname = itemView.findViewById(R.id.tv_stockname);
            tv_stockqut = itemView.findViewById(R.id.tv_stockqut);

        }
    }
}
