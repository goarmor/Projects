package com.company.superandyeyev.vkgroupreader.rest.api;

import com.company.superandyeyev.vkgroupreader.model.Group;
import com.company.superandyeyev.vkgroupreader.model.Member;
import com.company.superandyeyev.vkgroupreader.rest.model.response.BaseItemResponse;
import com.company.superandyeyev.vkgroupreader.rest.model.response.Full;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by DIMON on 05.10.2017.
 */

public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<Full<BaseItemResponse<Member>>> getMembers(@QueryMap Map<String, String> map);

    @GET(ApiMethods.GROUPS_GET_BY_ID)
    Observable<Full<List<Group>>> getById(@QueryMap Map<String, String> map);
}
