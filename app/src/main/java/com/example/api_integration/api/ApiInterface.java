package com.example.api_integration.api;

import static com.example.api_integration.api.Api.API_KEY;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import com.example.api_integration.model.imageModel;

import java.util.List;
import java.util.Locale;

public abstract class Api_Interface {

    @Headers("Authorization: Client-ID" + API_KEY)
    @GET("photos")
    public Call<List<imageModel>> getImages(
            @Query("page") int page,
            @Query("per_page") int per_page
    ) {
        return null;
    }
}
