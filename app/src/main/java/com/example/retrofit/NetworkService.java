package com.example.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkService {
    private static NetworkService instance;
    private static final String URL = "https://glabstore.blob.core.windows.net";
    private Retrofit retrofit;

    private NetworkService(){
        if(instance == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor);
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
    }

    static NetworkService getInstance(){
        if(instance == null){
            instance = new NetworkService();
        }
        return instance;
    }

    JSONPlaceHolderApi getJSONApi(){
        return retrofit.create(JSONPlaceHolderApi.class);
    }
}
