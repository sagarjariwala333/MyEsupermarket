package com.example.e_supermarket.customer.features;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.e_supermarket.R;
import com.example.e_supermarket.customer.features.fragments.CartFragment;
import com.example.e_supermarket.customer.features.fragments.HistoryFrgament;
import com.example.e_supermarket.customer.features.fragments.HomeFragment;
import com.example.e_supermarket.customer.features.fragments.ProfileFragment;
import com.example.e_supermarket.customer.features.fragments.ScannerFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.e_supermarket.R.id.itm_cart;
import static com.example.e_supermarket.R.id.itm_history;
//import static com.example.e_supermarket.R.id.tv_pid_alert;

public class HomeActivity extends AppCompatActivity
{

    private BottomNavigationView btmnav_cust;
    private FloatingActionButton fab_add;
    private TextView tv_pid_alert;
    private TextView tv_pname_alert;
    private TextView tv_price_alert;
    private ImageButton ib_add_alert;
    private ImageButton ib_minus_alert;
    private EditText et_disstock_alert;
    private ImageView iv_prod_alert;
    private Button btn_cancel_alert;
    private Button btn_addtocart_alert;
    private BottomAppBar btm_appcust;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fl_cust,new HomeFragment());
        transaction.commit();


        btmnav_cust=findViewById(R.id.btmnav_cust);
        fab_add=findViewById(R.id.fab_add);
        btmnav_cust.setOnNavigationItemSelectedListener(mListener);
        btm_appcust=findViewById(R.id.btmapp_cust);
       // fab_add=findViewById(R.id.fab_add);


        fab_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                FragmentManager manager=getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.fl_cust,new ScannerFragment());
                transaction.addToBackStack(null);
                transaction.commit();

               /* Dialog dialog=new Dialog(HomeActivity.this);
                dialog.setContentView(R.layout.popupview);
                dialog.setCancelable(true);
                dialog.getWindow().setLayout(1000,WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations= android.R.style.Animation_Dialog;

                tv_pid_alert=dialog.findViewById(R.id.tv_pid_alert);
                tv_pname_alert=dialog.findViewById(R.id.tv_pname_alert);
                tv_price_alert=dialog.findViewById(R.id.tv_price_alert);
                iv_prod_alert=dialog.findViewById(R.id.iv_prod_alert);
                ib_add_alert=dialog.findViewById(R.id.ib_add_alert);
                ib_minus_alert=dialog.findViewById(R.id.ib_minus_alert);
                et_disstock_alert=dialog.findViewById(R.id.et_disstock_alert);
                btn_addtocart_alert=dialog.findViewById(R.id.btn_addtocart_alert);
                btn_cancel_alert=dialog.findViewById(R.id.btn_cancel_alert);

                btn_addtocart_alert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                        FragmentManager manager=getSupportFragmentManager();
                        FragmentTransaction transaction=manager.beginTransaction();
                        transaction.replace(R.id.fl_cust,new CartFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });

                btn_cancel_alert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                ib_add_alert.setOnClickListener(new View.OnClickListener() {
                     @Override
                    public void onClick(View v)
                    {
                        int num= Integer.parseInt(et_disstock_alert.getText().toString());
                        num=num+1;
                        String str= String.valueOf(num);
                        et_disstock_alert.setText(str);
                    }
                });

                ib_minus_alert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        int num= Integer.parseInt(et_disstock_alert.getText().toString());
                        if (num!=0)
                        {
                            num = num - 1;
                            String str = String.valueOf(num);
                            et_disstock_alert.setText(str);
                        }
                    }
                });

                dialog.show();*/
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        btm_appcust.setVisibility(View.VISIBLE);
        fab_add.setVisibility(View.VISIBLE);

    }

    public void addone()
    {
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
    }


    private void Alert(View v)
    {
        final AlertDialog.Builder alert=new AlertDialog.Builder(HomeActivity.this);
        alert.setTitle("Alert");
        alert.setMessage("Product is unavailable");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        alert.create().show();
    }


    BottomNavigationView.OnNavigationItemSelectedListener mListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Fragment fragment=null;
            switch (item.getItemId())
            {
                case R.id.itm_home:
                    fragment=new HomeFragment ();
                    break;

                case itm_history:
                    fragment=new HistoryFrgament();
                    break;

                case R.id.itm_cart:
                    fragment=new CartFragment();
                    break;

                case R.id.itm_profile:
                    fragment=new ProfileFragment();
                    break;

                default:
                    return false;
            }

            FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.fl_cust,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
    };
}