package com.tmsnith.emotionsense.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {
    public  static ApiInterface getRetrofitService(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder oBuilder = new OkHttpClient.Builder();
        oBuilder.addNetworkInterceptor(loggingInterceptor);
        oBuilder.connectTimeout(60l, TimeUnit.SECONDS);
        oBuilder.readTimeout(60l,TimeUnit.SECONDS);

        oBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                Request request = original.newBuilder()
                       // .addHeader("Content-Type", "multipart/form-data")
                      //  .addHeader("Ocp-Apim-Subscription-Key", "b868ca80b2184f46a3ed464503ea4f2c")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });




        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.215:8000/main/")
                .addConverterFactory(GsonConverterFactory.create()).
                client(oBuilder.build()).
                build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        return service;
    }

}
