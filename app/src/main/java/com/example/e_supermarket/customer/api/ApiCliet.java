package com.example.e_supermarket.customer.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCliet
{
    public static final String BASE_URL="http://192.168.1.39/Admin/Esupermarket/";
    static Retrofit retrofit = null;
    static Gson gson = new GsonBuilder().setLenient().create();

    public static Retrofit getClient()
    {
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
