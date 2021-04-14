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

            }
        });


    }


    @Override
    public void onBackPressed() {
     //   super.onBackPressed();

        btm_appcust.setVisibility(View.VISIBLE);
        fab_add.setVisibility(View.VISIBLE);

        AlertDialog.Builder alertDialog= new AlertDialog.Builder(HomeActivity.this);
        alertDialog.setTitle("Exit");
        alertDialog.setMessage("Press exit button to exit");
        alertDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.setCancelable(true);
            }
        }).create().show();

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