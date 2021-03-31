package com.example.e_supermarket.customer.admin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.e_supermarket.customer.Common.Mob;
import com.example.e_supermarket.customer.Common.Variables;
import com.example.e_supermarket.customer.PrefUtil;
import com.example.e_supermarket.customer.admin.adapters.Staffadapter;
import com.example.e_supermarket.customer.admin.viewstaff.ViewStaffResponse;
import com.example.e_supermarket.customer.admin.models.Sportmodel;
import com.example.e_supermarket.customer.api.ApiCliet;
import com.example.e_supermarket.customer.api.ApiInterface;
import com.example.e_supermarket.customer.staff.fragments.StaffHomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

//import com.example.e_supermarket.customer.staff.fragments.StaffHomeFragment;

public class fragment_Staff extends Fragment
{


    private RecyclerView rv_staff;
    private ArrayList<Sportmodel> list;
    private Staffadapter madpter;
    private Toolbar tb_manage_staff;
    private FloatingActionButton fab_staff_add;

    public fragment_Staff() {
        // Required empty public constructor
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(SimpleCallback);
        itemTouchHelper.attachToRecyclerView(rv_staff);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__admin_staff, container, false);


        rv_staff =view.findViewById(R.id.rv_staff);
        fab_staff_add=view.findViewById(R.id.fab_staff_add);

        fab_staff_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.frame,new AddStaffFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        tb_manage_staff = view.findViewById(R.id.tb_manage_staff);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_manage_staff);
        setHasOptionsMenu(true);

        tb_manage_staff.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frame,new StaffHomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        //ItemTouchHelper itemTouchHelper=new
          //      ItemTouchHelper(SimpleCallback);
        //itemTouchHelper.attachToRecyclerView(rv_staff);
        setData();
        //setAdpter();


        return view;
    }


    ItemTouchHelper.SimpleCallback SimpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            switch (direction){
                case ItemTouchHelper.LEFT:
                    int position=viewHolder.getAdapterPosition();
                    String removedbillid=null;
                    removedbillid= list.get(position).getStaff_id();
                    Sportmodel removedbill=list.get(position);
                    list.remove(position);
                    madpter.notifyItemRemoved(position);
                    Snackbar make = Snackbar.make(rv_staff,"Bill id :-"+removedbillid, BaseTransientBottomBar.LENGTH_LONG);
                    make.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.add(position,removedbill);
                            madpter.notifyItemInserted(position);
                        }
                    }).show();
                    break;
            }
        }
    };

    private void setData()
    {

        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);


        apiInterface.viewstaff().enqueue(new Callback<ViewStaffResponse>() {
            @Override
            public void onResponse(Call<ViewStaffResponse> call, retrofit2.Response<ViewStaffResponse> response) {

                madpter = new Staffadapter(getActivity(),response.body().getSubarray());
                rv_staff.setAdapter(madpter);
                rv_staff.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                rv_staff.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            }

            @Override
            public void onFailure(Call<ViewStaffResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        getActivity().getMenuInflater().inflate(R.menu.signout,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.tool_signout:
                PrefUtil.removeString(Variables.userId,getActivity());
                PrefUtil.removeString(Variables.role,getActivity());
                PrefUtil.removeBoolean(Variables.isLoggedIn,getActivity());
                PrefUtil.clearPreference(getActivity());
                Intent intent=new Intent(getActivity(), Mob.class);
                startActivity(intent);
        }
        return true;
    }
}