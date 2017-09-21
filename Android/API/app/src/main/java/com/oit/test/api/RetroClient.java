package com.oit.test.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by optlptp156 on 9/4/2017.
 */

public class RetroClient {

    public static  String ROOT_URL = "http://profile.getsandbox.com/";


    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static APIService getApiService() {
        return getRetrofitInstance().create(APIService.class);
    }
}
