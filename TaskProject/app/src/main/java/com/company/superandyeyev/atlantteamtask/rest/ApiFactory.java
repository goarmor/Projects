package com.company.superandyeyev.atlantteamtask.rest;

import com.company.superandyeyev.atlantteamtask.common.ApiConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
/**
 * Created by DIMON on 10.10.2017.
 */

public class ApiFactory {

    private static ServiceApi sService;

    public static ServiceApi getServiceApi() {
        ServiceApi service = sService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sService;
                if (service == null) {
                    service = sService = buildRetrofit().create(ServiceApi.class);
                }
            }
        }
        return service;
    }


    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.SERVICE_BASE_URL)
                .build();
    }

}
