package com.jh.mvp.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWrapper {

    private static Retrofit.Builder builder = null;

    private static RetrofitWrapper retrofitWrapper = null;

    private static String url = null;

    private RetrofitWrapper() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new RetrofitLogInterceptor())
                .build();
        builder = new Retrofit.Builder();
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.client(okHttpClient);
    }

    public static RetrofitWrapper getInstance(String url) {
        RetrofitWrapper.url = url;
        if (retrofitWrapper == null) {
            synchronized (RetrofitWrapper.class) {
                if (retrofitWrapper == null) {
                    retrofitWrapper = new RetrofitWrapper();
                }
            }
        }
        return retrofitWrapper;
    }

    public <T> T createService(Class<T> clazz) {
        return builder.baseUrl(url).build().create(clazz);
    }


}
