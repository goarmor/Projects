package com.company.superandyeyev.vkgroupreader.rest.api;

import com.company.superandyeyev.vkgroupreader.rest.model.response.Full;
import com.company.superandyeyev.vkgroupreader.rest.model.response.VideosResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by DIMON on 28.10.2017.
 */

public interface VideoApi {
    @GET(ApiMethods.VIDEO_GET)
    Observable<Full<VideosResponse>> get(@QueryMap Map<String, String> map);
}
