package com.company.superandyeyev.vkgroupreader.rest.api;

import com.company.superandyeyev.vkgroupreader.model.CommentItem;
import com.company.superandyeyev.vkgroupreader.rest.model.response.Full;
import com.company.superandyeyev.vkgroupreader.rest.model.response.GetWallByIdResponse;
import com.company.superandyeyev.vkgroupreader.rest.model.response.GetWallResponse;
import com.company.superandyeyev.vkgroupreader.rest.model.response.ItemWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by DIMON on 29.09.2017.
 */

public interface WallApi {
    @GET(ApiMethods.WALL_GET)
    Observable<GetWallResponse> get(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_BY_ID)
    Observable<GetWallByIdResponse> getById(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}