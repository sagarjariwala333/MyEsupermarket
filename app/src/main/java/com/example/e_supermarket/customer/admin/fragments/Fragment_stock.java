package com.example.e_supermarket.customer.admin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.admin.adapters.Stockadapter;
import com.example.e_supermarket.customer.admin.models.stockmodel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class Fragment_stock extends Fragment
{



    private RecyclerView rv_stock;
    private ArrayList<stockmodel> list;
    private Stockadapter madpter;
    private Toolbar tb_manage_stock;


    public Fragment_stock() {
        // Required empty public constructor
        //ItemTouchHelper itemTouchHelper = new ItemTouchHelper(SimpleCallback);
        //itemTouchHelper.attachToRecyclerView(rv_stock);
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_stock, container, false);

        rv_stock = view.findViewById(R.id.rv_stock);


        tb_manage_stock = view.findViewById(R.id.tb_manage_stock);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_manage_stock);
        setHasOptionsMenu(true);

        tb_manage_stock.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.frame,new AdminHomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        setData();
        setAdpter();
        ItemTouchHelper itemTouchHelper=new
                ItemTouchHelper(SimpleCallback);
        itemTouchHelper.attachToRecyclerView(rv_stock);
        return view;



    }


    ItemTouchHelper.SimpleCallback SimpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    int position = viewHolder.getAdapterPosition();
                    String removedbillid = null;
                    removedbillid = list.get(position).getStock_id();
                    stockmodel removedbill = list.get(position);
                    list.remove(position);
                    madpter.notifyItemRemoved(position);
                    Snackbar make = Snackbar.make(rv_stock, "Bill id :-" + removedbillid, BaseTransientBottomBar.LENGTH_LONG);
                    make.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.add(position, removedbill);
                            madpter.notifyItemInserted(position);
                        }
                    }).show();
                    break;
            }
        }
    };




    private void setData() {
        list = new ArrayList<>();
        stockmodel model = new stockmodel();
        model.setStock_Image(R.drawable.ic_baseline_group);
        model.setStock_Name("Niharika ");
        model.setStock_id("P00001");
        model.setStock_qut("1000");
        list.add(model);

        stockmodel model1 = new stockmodel();
        model1.setStock_Image(R.drawable.ic_baseline_person);
        model1.setStock_Name("Niharika ");
        model.setStock_id("P00002");
        model.setStock_qut("10000");
        list.add(model1);

    }

    private void setAdpter() {
        madpter = new Stockadapter(Fragment_stock.this, list);
        rv_stock.setAdapter(madpter);
        rv_stock.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rv_stock.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }
}