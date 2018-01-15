package com.moggot.spyagent.data.network;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterseptor implements Interceptor {

    @NonNull
    private PreferenceRepo preferenceRepo;

    public AuthInterseptor(@NonNull PreferenceRepo preferenceRepo) {
        this.preferenceRepo = preferenceRepo;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("user_id", String.valueOf(preferenceRepo.getUser().getUserId()))
                .build();
        return chain.proceed(request);
    }
}