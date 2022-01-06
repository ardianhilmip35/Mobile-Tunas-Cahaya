package com.ardianhilmip.tunascahaya;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL="https://ws-tif.com/tunas-cahaya-build/retrofit/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;
    private static Retrofit IMAGE;

    private OkHttpClient.Builder builder = new OkHttpClient.Builder();

    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    public RetrofitClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();


    }

    public static synchronized RetrofitClient getInstance(){

        if(retrofitClient==null){
            retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }

    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }
}