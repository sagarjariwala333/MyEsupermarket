package com.example.e_supermarket.customer.features.fragments;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.example.e_supermarket.customer.Common.Variables;
import com.example.e_supermarket.customer.PrefUtil;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.features.adapters.History_adapter;
import com.example.e_supermarket.customer.features.models.History_model;
import com.example.e_supermarket.customer.features.oldorders.OldOrderResponse;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        View view = inflater.inflate(R.layout.fragment_history_frgament, container, false);
        rv_history = view.findViewById(R.id.rv_history);
        tb_his = view.findViewById(R.id.tb_his);

        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(tb_his);

        tb_his.setNavigationOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fl_cust, new HomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        setdata();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv_history);

        return view;
    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    int position = viewHolder.getAdapterPosition();
                    String removedbillid = null;
                    removedbillid = list.get(position).getBill_id();
                    History_model removedbill = list.get(position);
                    list.remove(position);
                    mAdapter.notifyItemRemoved(position);

                    Snackbar make = Snackbar.make(rv_history, "Bill ID :- " + removedbillid,
                            BaseTransientBottomBar.LENGTH_LONG);
                    make.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.add(position, removedbill);
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
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.remove_item))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };


    private void setdata() {
        list = new ArrayList<>();


        ApiInterface apiInterface = ApiCliet.getClient().create(ApiInterface.class);

        Toast.makeText(getActivity(), ""+ PrefUtil.getstringPref(Variables.userId,HistoryFrgament.this.getActivity()), Toast.LENGTH_SHORT).show();
        apiInterface.getOldOrder(PrefUtil.getstringPref(Variables.userId,HistoryFrgament.this.getActivity())).enqueue(new Callback<OldOrderResponse>() {
            @Override
            public void onResponse(Call<OldOrderResponse> call, Response<OldOrderResponse> response) {
                mAdapter = new History_adapter(HistoryFrgament.this, response.body().getSubarray());
                rv_history.setAdapter(mAdapter);
                rv_history.setLayoutManager(new LinearLayoutManager(getContext()));
                rv_history. addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


            }

            @Override
            public void onFailure(Call<OldOrderResponse> call, Throwable t) {

            }
        });
    }
}