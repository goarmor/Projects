package com.company.superandyeyev.vkgroupreader.rest.api;

import com.company.superandyeyev.vkgroupreader.rest.model.response.Full;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by DIMON on 31.10.2017.
 */

public interface AccountApi {
    @GET(ApiMethods.ACCOUNT_REGISTER_DEVICE)
    Observable<Full<Integer>> registerDevice(@QueryMap Map<String, String> map);
}
