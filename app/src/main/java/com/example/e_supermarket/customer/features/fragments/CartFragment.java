package com.example.e_supermarket.customer.features.fragments;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    private int pos;

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
        btn_removeall.setEnabled(false);
        btn_checkout.setEnabled(false);
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
                final AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                alert.setTitle("Remove All");
                alert.setMessage("Remove all items from cart");
                alert.setPositiveButton("Remove All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        met_removeall();

                    }

                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.create().show();

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

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv_cart);
        return view;
    }


    private void met_removeall()
    {

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
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                            else
                            {
                                Toast.makeText(getActivity(), ""+removeAllResponse.getMsg(), Toast.LENGTH_SHORT).show();
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
                    met_cartitemremove(list.get(position).getProductId());
                    pos=position;
                    list.remove(position);
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
                        mAdapter.notifyItemRemoved(pos);
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
                if(response.isSuccessful() && response.body() != null) {
                    list = response.body().getSubarray();

                    if (list != null && !list.isEmpty())
                    {
                        Log.d("Null1","null1");
                        Toast.makeText(getActivity(), "Null1", Toast.LENGTH_SHORT).show();
                        btn_removeall.setEnabled(true);
                        btn_checkout.setEnabled(true);
                        mAdapter = new Cart_adapter(CartFragment.this, list);
                        rv_cart.setAdapter(mAdapter);
                        rv_cart.setLayoutManager(new LinearLayoutManager(getContext()));
                        rv_cart.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                    }
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
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