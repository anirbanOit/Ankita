package com.oit.test.api;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://profile.getsandbox.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}