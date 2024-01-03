package com.example.api_integration.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static final String BASE_URL = "YOUR_BASE_URL";
    public static final String API_KEY = "YOUR_API_KEY";

    public static Retrofit retrofit = null;

    public static Api_Interface getApiInterface(){
        if(retrofit == null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit.create(Api_Interface.class);
    }

}
