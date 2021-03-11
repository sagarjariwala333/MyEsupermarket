package com.example.e_supermarket.customer.admin.adapters;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.admin.fragments.UpdateProfileFragment;
import com.example.e_supermarket.customer.admin.fragments.fragment_Staff;
import com.example.e_supermarket.customer.admin.models.Sportmodel;

import java.util.ArrayList;

public class Staffadapter extends RecyclerView.Adapter<Staffadapter.MyViewHolder> {
    private final fragment_Staff mActivity;
    private final ArrayList<Sportmodel> list;

    public Staffadapter(fragment_Staff fragment_staff, ArrayList<Sportmodel> list) {
        this.mActivity=fragment_staff;
        this.list=list;
    }

    @NonNull
    @Override
    public Staffadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onecell_admin_staff,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Staffadapter.MyViewHolder holder, int position)
    {


        holder.iv_staff.setImageResource(list.get(position).getStaff_img());
        holder.tv_staffid.setText(list.get(position).getStaff_id());
        holder.tv_staffname.setText(list.get(position).getStaff_name());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                final AlertDialog.Builder alert=new AlertDialog.Builder(mActivity.getActivity());
                alert.setTitle("Staff Id :- ");
                alert.setMessage("Staff Name :- ");

                alert.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_staff;
        private final TextView tv_staffid;
        private final TextView tv_staffname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            iv_staff=itemView.findViewById(R.id.iv_staff);
            tv_staffid=itemView.findViewById(R.id.tv_staffid);
            tv_staffname=itemView.findViewById(R.id.tv_staffname);
        }
    }
}
