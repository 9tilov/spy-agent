package com.moggot.spyagent.data.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterseptor implements Interceptor {

    @NonNull
    private CommonServerModel commonServerModel;

    public AuthInterseptor(@NonNull CommonServerModel commonServerModel) {
        this.commonServerModel = commonServerModel;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("user_id", String.valueOf(commonServerModel.getUserId()))
                .build();
        return chain.proceed(request);
    }
}