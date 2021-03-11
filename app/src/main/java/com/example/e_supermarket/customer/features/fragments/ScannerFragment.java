package com.example.e_supermarket.customer.features.fragments;

import android.Manifest;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.e_supermarket.R;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class ScannerFragment extends Fragment {

    private CodeScanner mCodeScanner;

    public String scanned_itm;

    private TextView tv_pid_alert;
    private TextView tv_pname_alert;
    private TextView tv_price_alert;
    private ImageButton ib_add_alert;
    private ImageButton ib_minus_alert;
    private EditText et_disstock_alert;
    private ImageView iv_prod_alert;
    private Button btn_cancel_alert;
    private Button btn_addtocart_alert;

    public ScannerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_scanner, container, false);

        CodeScannerView scannerView=view.findViewById(R.id.scanner);

        //getActivity().Dexter.withContext(this);
        Dexter.withContext(getActivity())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse)
                    {

                        mCodeScanner=new CodeScanner(getActivity(),scannerView);
                        mCodeScanner.setDecodeCallback(new DecodeCallback() {
                            @Override
                            public void onDecoded(@NonNull Result result) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d("result_scanner",""+result.getText());
                                        scanned_itm=result.getText().toString();
                                        Toast.makeText(getActivity(), ""+result.getText(), Toast.LENGTH_SHORT).show();
                                        if (!scanned_itm.isEmpty())
                                        {
                                            FragmentManager manager=getActivity().getSupportFragmentManager();
                                            FragmentTransaction transaction=manager.beginTransaction();
                                            transaction.replace(R.id.fl_cust,new HomeFragment<>());
                                            transaction.addToBackStack(null);
                                            transaction.commit();

                                            Dialog dialog=new Dialog(getActivity());
                                            dialog.setContentView(R.layout.popupview);
                                            dialog.setCancelable(true);
                                            dialog.getWindow().setLayout(1000, WindowManager.LayoutParams.WRAP_CONTENT);
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


                                            tv_pid_alert.setText(scanned_itm);


                                            btn_addtocart_alert.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v)
                                                {
                                                    dialog.dismiss();
                                                    FragmentManager manager=getActivity().getSupportFragmentManager();
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

                                            dialog.show();
                                        }
                                    }
                                });
                            }
                        });

                        mCodeScanner.startPreview();
                       /* scannerView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mCodeScanner.startPreview();
                            }
                        });*/

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse)
                    {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }

                }).check();





        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
//        mCodeScanner.startPreview();
    }
}