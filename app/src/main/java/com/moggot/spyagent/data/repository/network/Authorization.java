package com.moggot.spyagent.data.repository.network;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.api.SpyApi;
import com.moggot.spyagent.data.local.UserModel;
import com.moggot.spyagent.data.model.LoginResponse;
import com.moggot.spyagent.data.network.CommonServerModel;
import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

import io.reactivex.Single;

public class Authorization {

    @NonNull
    private SpyApi spyApi;
    @NonNull
    private PreferenceRepo preferenceRepo;
    @NonNull
    private CommonServerModel commonServerModel;

    public Authorization(@NonNull SpyApi spyApi, @NonNull PreferenceRepo preferenceRepo, CommonServerModel commonServerModel) {
        this.spyApi = spyApi;
        this.preferenceRepo = preferenceRepo;
        this.commonServerModel = commonServerModel;
    }

    public Single<LoginResponse> login(long userId, String accessToken) {
        UserModel user = new UserModel(userId, accessToken);
        preferenceRepo.saveUser(user);
        commonServerModel.setUserId(user.getUserId());
        return spyApi.login(accessToken);
    }

    public Single<LoginResponse> logout() {
        return spyApi.logout();
    }
}
