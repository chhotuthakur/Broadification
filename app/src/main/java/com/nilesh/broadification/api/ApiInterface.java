package com.nilesh.broadification.api;

import static com.nilesh.broadification.models.Constants.CONTENT_TYPE;
import static com.nilesh.broadification.models.Constants.SERVER_KEY;

import com.nilesh.broadification.models.PushNOtification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Authorization:key="+ SERVER_KEY,"Content_Type:="+CONTENT_TYPE})
    @POST("fcm/send")

    Call<PushNOtification>sendNotification(@Body PushNOtification nOtification);
}
