package com.example.e_supermarket.customer.temp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_supermarket.R;

import java.util.List;


public class RecyclerFragment extends Fragment {


    private RecyclerView rv;
    private List<RecyclerResponse> list=null;
    private RecyclerAdapter madapter;

    public RecyclerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        rv=view.findViewById(R.id.rv);
        //setdata();
        return view;
    }

  /*  private void setdata()
    {

        ApiInterface apiInterface= ApiCliet.getClient().create(ApiInterface.class);

        apiInterface.recycler().enqueue(new Callback<RecyclerResponse>() {
            @Override
            public void onResponse(Call<RecyclerResponse> call, Response<RecyclerResponse> response) {
                //list=response.body().getSubarray();
                madapter=new RecyclerAdapter(getActivity(),response.body().getSubarray());
                rv.setAdapter(madapter);
                rv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                rv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            }

            @Override
            public void onFailure(Call<RecyclerResponse> call, Throwable t) {

            }
        });
    }*/
}