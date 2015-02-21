package com.thetechboys.submission.service;


import com.thetechboys.submission.model.Submission;


import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by niraj.kumar.chauhan on 09-02-2015.
 */
public interface Service {

    @GET("/submission")
    public void getSubmissions(Callback<List<Submission>> cb);

    @FormUrlEncoded
    @POST("/post")
    public void postSubmission(@Field("postTxt") String post,Callback<JSONObject> cb);
}
