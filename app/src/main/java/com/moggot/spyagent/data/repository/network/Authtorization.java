package com.moggot.spyagent.data.repository.network;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.api.SpyApi;
import com.moggot.spyagent.data.local.UserModel;
import com.moggot.spyagent.data.model.LoginResponse;
import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

import io.reactivex.Single;

public class Authtorization {

    @NonNull
    private SpyApi spyApi;
    @NonNull
    private PreferenceRepo preferenceRepo;

    public Authtorization(@NonNull SpyApi spyApi, @NonNull PreferenceRepo preferenceRepo) {
        this.spyApi = spyApi;
        this.preferenceRepo = preferenceRepo;
    }

    public Single<LoginResponse> login(long userId, String accessToken) {
        UserModel user = new UserModel(userId, accessToken);
        preferenceRepo.saveUser(user);
        return spyApi.login(userId, accessToken);
    }

    public Single<LoginResponse> logout() {
        UserModel user = preferenceRepo.getUser();
        return spyApi.logout(user.getUserId(), user.getAccessToken());
    }
}
