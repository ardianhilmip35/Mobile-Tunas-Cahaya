package com.ardianhilmip.mobiletunascahaya.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL="https://ws-tif.com/tunas-cahaya-build/Tunas%20Cahaya%20Build/retrofit/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;

    public static Retrofit RetrofitClient(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }
}
