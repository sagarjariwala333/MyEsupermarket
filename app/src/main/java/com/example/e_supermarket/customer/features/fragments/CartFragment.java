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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.adapters.Cart_adapter;
import com.example.e_supermarket.customer.features.models.Cart_model;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CartFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    private RecyclerView rv_cart;
    private ArrayList<Cart_model> list=new ArrayList<Cart_model>();
    private Cart_adapter mAdapter;
    private Button btn_checkout;
    private BottomAppBar btmapp_cust;
    private BottomNavigationView btm_navitm;
    private FloatingActionButton fab_add;
    private Button place_order;
    private Toolbar tb_cart;

    public CartFragment()
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
        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        rv_cart=view.findViewById(R.id.rv_cart);
        btn_checkout=view.findViewById(R.id.btn_checkout);
        btm_navitm=getActivity().findViewById(R.id.btmnav_cust);
        btmapp_cust=getActivity().findViewById(R.id.btmapp_cust);
        fab_add=getActivity().findViewById(R.id.fab_add);
        tb_cart=view.findViewById(R.id.tb_cart);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_cart);


        tb_cart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new HomeFragment());
                transaction.commit();
                fab_add.setVisibility(View.VISIBLE);
                btmapp_cust.setVisibility(View.VISIBLE);
            }
        });



        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment=new PlaceOrdFragment();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new PlaceOrdFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        /*place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new PlaceOrdFragment());
                transaction.commit();
            }
        });*/

        //Making Bottom Appbar and floating action button invisible
        btmapp_cust.setVisibility(View.INVISIBLE);
        fab_add.setVisibility(View.INVISIBLE);

        setdata();
        setadapter();

        //Clicked on place order button

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv_cart);
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
                    String removedpname=list.get(position).getP_name();
                    Cart_model removedcartprod=list.get(position);

                    //Removing item
                    list.remove(position);
                    mAdapter.notifyItemRemoved(position);

                    Snackbar make=Snackbar.make(rv_cart,removedpname, BaseTransientBottomBar.LENGTH_LONG);

                    make.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.add(position,removedcartprod);
                            mAdapter.notifyItemInserted(position);
                        }
                    }).show();

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
        mAdapter=new Cart_adapter(CartFragment.this,list);
        rv_cart.setAdapter(mAdapter);
        rv_cart.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_cart.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }

    private void setdata()
    {
        Cart_model model1=new Cart_model();
        model1.setP_img(R.drawable.hideseek);
        model1.setP_id("1000001");
        model1.setP_name("Hide & Seek");
        model1.setPrice("50");
        list.add(model1);

        Cart_model model2=new Cart_model();
        model2.setP_img(R.drawable.ic_launcher_background);
        model2.setP_id("1000002");
        model2.setP_name("Dark Fantacy");
        model2.setPrice("50");
        list.add(model2);

        Cart_model model3=new Cart_model();
        model3.setP_img(R.drawable.ic_launcher_background);
        model3.setP_id("1000003");
        model3.setP_name("Good day");
        model3.setPrice("50");
        list.add(model3);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.itm_cart:
                Toast.makeText(getActivity(), "Backed 1", Toast.LENGTH_SHORT).show();

            case R.id.itm_home:
                Toast.makeText(getActivity(), "Backed 2", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}