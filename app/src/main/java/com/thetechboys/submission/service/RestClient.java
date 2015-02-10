package com.thetechboys.submission.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by LENOVO PC on 09-02-2015.
 */
public class RestClient {
    private static final String BASE_URL = "http://techboys.16mb.com/webservice/index.php";
    private Service apiService;

    public RestClient()
    {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(Service.class);
    }

    public Service getApiService()
    {
        return apiService;
    }

}
