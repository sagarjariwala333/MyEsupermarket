package com.example.e_supermarket.customer.admin.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import com.example.e_supermarket.customer.admin.adapters.productadpter;
import com.example.e_supermarket.customer.admin.models.productmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class productfragment extends Fragment {


    private RecyclerView rv_product;
    private ArrayList<productmodel> list;
    private productadpter madpter;
    private Toolbar tb_manage_prod;
    private FloatingActionButton fab_prod_add;

    public productfragment() {
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
        View view = inflater.inflate(R.layout.fragment_admin_manageproduct, container, false);


        rv_product = view.findViewById(R.id.rv_product);
        fab_prod_add = view.findViewById(R.id.fab_prod_add);
        tb_manage_prod = view.findViewById(R.id.tb_manage_prod);
        ((AppCompatActivity) getActivity()).setSupportActionBar(tb_manage_prod);
        setHasOptionsMenu(true);


        fab_prod_add.setOnClickListener(new View.OnClickListener() {
            private EditText et_mng_prodprice;
            private EditText et_mng_prodtype;
            private Button btn_mng_up_prodimg;
            private Button btn_mng_cancel;
            private Button btn_mng_addstaff;
            private EditText et_mng_prodqut;
            private EditText et_mng_prodname;
            private EditText et_mng_prodid;

            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_prod);
                dialog.setCancelable(true);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                //et_mng_prodid = dialog.findViewById(R.id.et_mng_prodid);
                et_mng_prodname = dialog.findViewById(R.id.et_mng_prodname);
                et_mng_prodqut = dialog.findViewById(R.id.et_mng_prodqut);
                et_mng_prodtype=dialog.findViewById(R.id.et_mng_prodtype);
                et_mng_prodprice=dialog.findViewById(R.id.et_mng_prodprice);
                btn_mng_up_prodimg=dialog.findViewById(R.id.btn_mng_up_prodimg);
                btn_mng_addstaff = dialog.findViewById(R.id.btn_mng_addstaff);
                btn_mng_cancel = dialog.findViewById(R.id.btn_mng_cancel);
                btn_mng_up_prodimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uploadproimg();
                    }
                });
                btn_mng_addstaff.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                btn_mng_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });


        tb_manage_prod.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frame, new AdminHomeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        setdata();
        setadapter();


     //   ItemTouchHelper itemTouchHelper = new
       //         ItemTouchHelper(simpleCallback);
        //itemTouchHelper.attachToRecyclerView(rv_product);


        return view;
    }

    private void uploadproimg() {

    }

    private void setadapter() {


        madpter = new productadpter(productfragment.this, list);
        rv_product.setAdapter(madpter);
        rv_product.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rv_product.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

    }

    private void setdata() {
        list = new ArrayList<>();
        productmodel model = new productmodel();
        model.setImage(R.drawable.ic_baseline_person);
        model.setId("1wcfv");
        model.setName("xyz");
        model.setPrice("1000");
        list.add(model);


        productmodel model1 = new productmodel();
        model1.setImage(R.drawable.ic_baseline_person);
        model1.setId("1wcfv");
        model1.setName("xyz");
        model1.setPrice("1000");
        list.add(model1);

        productmodel model2 = new productmodel();
        model2.setImage(R.drawable.ic_baseline_person);
        model2.setId("1wcfv");
        model2.setName("xyz");
        model2.setPrice("1000");
        list.add(model2);

    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
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
                    removedbillid = list.get(position).getId();
                    productmodel removedbill = list.get(position);
                    list.remove(position);
                    madpter.notifyItemRemoved(position);
                    Snackbar make = Snackbar.make(rv_product, "Bill id :-" + removedbillid, BaseTransientBottomBar.LENGTH_LONG);
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
}


