package com.moggot.spyagent.data.api;

import android.support.annotation.WorkerThread;

import com.moggot.spyagent.R;
import com.moggot.spyagent.data.model.LoginResponse;
import com.moggot.spyagent.data.model.SelfResponseModel;
import com.moggot.spyagent.data.model.UserResponseModel;
import com.moggot.spyagent.presentation.App;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SpyApi {

    String BASE_SPY_URL = App.getInstance().getApplicationContext().getString(R.string.base_server_url);

    @POST("Login")
    @WorkerThread
    Single<LoginResponse> login(@Query("access_token") String access_token);

    @POST("Logout")
    @WorkerThread
    Single<LoginResponse> logout();

    @GET("GetSelfInfo")
    @WorkerThread
    Single<SelfResponseModel> getSelfInfo();
}
