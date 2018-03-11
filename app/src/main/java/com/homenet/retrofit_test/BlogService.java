package com.homenet.retrofit_test;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by weijunpeng on 2018/3/11.
 */

public interface BlogService {
    @GET("query?type=yuantong&postid=11111111111")
    Call<Reception> getBlog();
}
