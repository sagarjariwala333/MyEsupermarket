package com.example.e_supermarket.customer.features.fragments;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.features.adapters.Cart_adapter;
import com.example.e_supermarket.customer.features.cartresponse.CartResponse;
import com.example.e_supermarket.customer.features.cartresponse.RemoveAllResponse;
import com.example.e_supermarket.customer.features.cartresponse.RemoveResponse;
import com.example.e_supermarket.customer.features.cartresponse.SubarrayItem;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    private RecyclerView rv_cart;
    private List<SubarrayItem> list;
    private Cart_adapter mAdapter;
    private Button btn_checkout;
    private BottomAppBar btmapp_cust;
    private BottomNavigationView btm_navitm;
    private FloatingActionButton fab_add;
    private Button place_order;
    private Toolbar tb_cart;
    String user_id;
    private Button btn_removeall;

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
        btn_removeall=view.findViewById(R.id.btn_removeall);
        user_id=getActivity().getIntent().getStringExtra("user_id");
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_cart);

        setdata();
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


        btn_removeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                met_removeall();
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



        //Clicked on place order button

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv_cart);
        return view;
    }

    private void met_removeall() {
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.removeallcart(getActivity().getIntent().getStringExtra("user_id"))
                .enqueue(new Callback<RemoveAllResponse>() {
                    @Override
                    public void onResponse(Call<RemoveAllResponse> call, Response<RemoveAllResponse> response) {
                        if (response.isSuccessful() && response.body()!=null)
                        {
                            RemoveAllResponse removeAllResponse=response.body();
                            if (removeAllResponse.getSuccess()==1)
                            {
                                FragmentManager manager=getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction=manager.beginTransaction();
                                transaction.replace(R.id.fl_cust,new CartFragment());
                                transaction.commit();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RemoveAllResponse> call, Throwable t) {

                    }
                });
    }

    final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    int position = viewHolder.getAdapterPosition();
                    String removedpname = list.get(position).getProductName();
                    SubarrayItem removedcartprod = list.get(position);

                    //Removing item
                    list.remove(position);
                    met_cartitemremove(list.get(position).getProductId());
                    FragmentManager manager=getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.replace(R.id.fl_cust,new CartFragment());
                    transaction.commit();
                    //mAdapter.notifyItemRemoved(position);
                    Snackbar make = Snackbar.make(rv_cart, removedpname, BaseTransientBottomBar.LENGTH_LONG);

                    make.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.add(position, removedcartprod);
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
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.remove_item))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private void met_cartitemremove(String productId)
    {
        ApiInterface apiInterface=ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.removecart(getActivity().getIntent().getStringExtra("user_id"),productId)
                .enqueue(new Callback<RemoveResponse>() {
                    @Override
                    public void onResponse(Call<RemoveResponse> call, Response<RemoveResponse> response) {
                        Toast.makeText(getContext(), "Removed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<RemoveResponse> call, Throwable t) {

                    }
                });
    }

    private void setdata()
    {

        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);
        apiInterface.getCart(getActivity().getIntent().getStringExtra("user_id")).enqueue(new Callback<CartResponse>()
        {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response)
            {
                    list=response.body().getSubarray();
                    mAdapter = new Cart_adapter(CartFragment.this, list);
                    rv_cart.setAdapter(mAdapter);
                    rv_cart.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {

            }
        });
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