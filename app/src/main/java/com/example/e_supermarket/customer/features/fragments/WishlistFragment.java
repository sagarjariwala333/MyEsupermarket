package com.example.e_supermarket.customer.features.fragments;

import android.graphics.Canvas;
import android.os.Bundle;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.adapters.Wishlist_adapter;
import com.example.e_supermarket.customer.features.models.Cart_model;
import com.example.e_supermarket.customer.features.models.Wishlist_model;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class WishlistFragment extends Fragment
{

    private String mParam1;
    private String mParam2;
    private RecyclerView rv_wishlist;
    private ArrayList<Wishlist_model> list;
    private Wishlist_adapter wish_dapter;
    private Toolbar tb_wishlist;

    public WishlistFragment()
    {
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
        View view=inflater.inflate(R.layout.fragment_wishlist, container, false);
        rv_wishlist=view.findViewById(R.id.rv_wishlist);
        tb_wishlist=view.findViewById(R.id.tb_wishlist);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_wishlist);

        tb_wishlist.setNavigationOnClickListener(new View.OnClickListener() {
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

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv_wishlist);
        setdata();
        setadapter();
        return view;
    }


    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
        {

            switch (direction)
            {
                case ItemTouchHelper.LEFT:
                    int position=viewHolder.getAdapterPosition();
                    String removedwishpname=list.get(position).getWish_pname();
                    Wishlist_model removedwishprod=list.get(position);

                    //Removing item
                    list.remove(position);
                    wish_dapter.notifyItemRemoved(position);

                    Snackbar make=Snackbar.make(rv_wishlist,removedwishpname, BaseTransientBottomBar.LENGTH_LONG);

                    make.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.add(position,removedwishprod);
                            wish_dapter.notifyItemInserted(position);
                        }
                    }).show();

            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

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
        wish_dapter =new Wishlist_adapter(WishlistFragment.this,list);
        rv_wishlist.setAdapter(wish_dapter);
        rv_wishlist.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_wishlist.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }

    private void setdata()
    {
        list=new ArrayList<>();

        Wishlist_model model1=new Wishlist_model();
        model1.setWish_pimg(R.drawable.ic_launcher_background);
        model1.setWish_pid("P00001");
        model1.setWish_pname("Hide & Seek");
        model1.setWish_pprice("50");
        list.add(model1);

        Wishlist_model model2=new Wishlist_model();
        model2.setWish_pimg(R.drawable.ic_launcher_background);
        model2.setWish_pid("P00001");
        model2.setWish_pname("Hide & Seek");
        model2.setWish_pprice("50");
        list.add(model2);

        Wishlist_model model3=new Wishlist_model();
        model3.setWish_pimg(R.drawable.ic_launcher_background);
        model3.setWish_pid("P00001");
        model3.setWish_pname("Hide & Seek");
        model3.setWish_pprice("50");
        list.add(model3);

        Wishlist_model model4=new Wishlist_model();
        model4.setWish_pimg(R.drawable.ic_launcher_background);
        model4.setWish_pid("P00001");
        model4.setWish_pname("Hide & Seek");
        model4.setWish_pprice("50");
        list.add(model4);
    }
}