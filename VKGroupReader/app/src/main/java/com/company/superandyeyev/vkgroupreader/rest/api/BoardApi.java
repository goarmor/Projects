package com.company.superandyeyev.vkgroupreader.rest.api;

import com.company.superandyeyev.vkgroupreader.model.CommentItem;
import com.company.superandyeyev.vkgroupreader.model.Topic;
import com.company.superandyeyev.vkgroupreader.rest.model.response.BaseItemResponse;
import com.company.superandyeyev.vkgroupreader.rest.model.response.Full;
import com.company.superandyeyev.vkgroupreader.rest.model.response.ItemWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by DIMON on 26.10.2017.
 */

public interface BoardApi {
    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);

    @GET(ApiMethods.BOARD_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
