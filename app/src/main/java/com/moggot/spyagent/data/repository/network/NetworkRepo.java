package com.moggot.spyagent.data.repository.network;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.api.SpyApi;
import com.moggot.spyagent.data.model.SelfResponseModel;
import com.moggot.spyagent.data.model.UserResponseModel;
import com.moggot.spyagent.data.repository.DataRepoImpl;
import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

import io.reactivex.Single;

public class NetworkRepo implements DataRepoImpl {

    @NonNull
    private SpyApi spyApi;
    @NonNull
    private PreferenceRepo preferenceRepo;

    public NetworkRepo(@NonNull SpyApi spyApi, @NonNull PreferenceRepo preferenceRepo) {
        this.spyApi = spyApi;
        this.preferenceRepo = preferenceRepo;
    }

    @Override
    public Single<SelfResponseModel> getSelfInfo() {
        return spyApi.getSelfInfo();
    }
}
