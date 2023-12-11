package com.example.api_integration.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static final String BASE_URL = "https://api.unsplash.com/";
    public static final String API_KEY = "AenXbeYDlN_qxGayMAMF1e19vhVxCxJvz7PJw_G-5JE";

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
