package com.example.e_supermarket.customer.features.fragments;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.adapters.History_adapter;
import com.example.e_supermarket.customer.features.models.History_model;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class HistoryFrgament extends Fragment {

    private String mParam1;
    private String mParam2;
    private RecyclerView rv_history;
    private ArrayList<History_model> list;
    private History_adapter mAdapter;
    private Toolbar tb_his;


    public HistoryFrgament() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_history_frgament, container, false);
        rv_history=view.findViewById(R.id.rv_history);
        tb_his=view.findViewById(R.id.tb_his);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_his);

        tb_his.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new HomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        setdata();
        setadapter();

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv_history);

        return view;
    }


    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT)
    {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
        {

            switch (direction)
            {
                case ItemTouchHelper.LEFT:
                    int position=viewHolder.getAdapterPosition();
                    String removedbillid=null;
                    removedbillid= list.get(position).getBill_id();
                    History_model removedbill=list.get(position);
                    list.remove(position);
                    mAdapter.notifyItemRemoved(position);

                    Snackbar make = Snackbar.make(rv_history, "Bill ID :- "+removedbillid,
                            BaseTransientBottomBar.LENGTH_LONG);
                    make.setAction("Undo", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            list.add(position,removedbill);
                            mAdapter.notifyItemInserted(position);
                        }
                    }).show();
                    break;
            }

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(),R.color.remove_item))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private void setadapter()
    {
        mAdapter=new History_adapter(HistoryFrgament.this,getActivity(),list);
        rv_history.setAdapter(mAdapter);
        rv_history.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_history.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }

    private void setdata()
    {
        list=new ArrayList<>();

        History_model model1=new History_model();
       // model1.setBill_img(R.drawable.ic_launcher_background);
        model1.setBill_id("B000001");
        model1.setBill_date("1-1-2021");
        model1.setBill_amt("5000");
        list.add(model1);

        History_model model2=new History_model();
     //   model2.setBill_img(R.drawable.ic_launcher_background);
        model2.setBill_id("B000002");
        model2.setBill_date("1-1-2021");
        model2.setBill_amt("5000");
        list.add(model2);

        History_model model3=new History_model();
       // model3.setBill_img(R.drawable.ic_launcher_background);
        model3.setBill_id("B000003");
        model3.setBill_date("1-1-2021");
        model3.setBill_amt("5000");
        list.add(model3);

        History_model model4=new History_model();
       // model4.setBill_img(R.drawable.ic_launcher_background);
        model4.setBill_id("B000003");
        model4.setBill_date("1-1-2021");
        model4.setBill_amt("5000");
        list.add(model4);
    }
}