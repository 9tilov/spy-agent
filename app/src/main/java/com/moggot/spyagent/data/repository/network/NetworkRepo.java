package com.moggot.spyagent.data.repository.network;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.model.LoginResponse;
import com.moggot.spyagent.data.model.SelfModel;
import com.moggot.spyagent.data.model.TopOfLikes;
import com.moggot.spyagent.data.network.api.SpyApi;
import com.moggot.spyagent.data.repository.DataRepoImpl;
import com.moggot.spyagent.data.repository.local.DatabaseRepo;

import io.reactivex.Single;
import timber.log.Timber;

public class NetworkRepo implements DataRepoImpl {

    @NonNull
    private SpyApi spyApi;
    @NonNull
    private DatabaseRepo databaseRepo;

    public NetworkRepo(@NonNull SpyApi spyApi, @NonNull DatabaseRepo databaseRepo) {
        this.spyApi = spyApi;
        this.databaseRepo = databaseRepo;
    }

    public Single<LoginResponse> login(String accessToken) {
        return spyApi.login(accessToken);
    }

    public Single<LoginResponse> logout() {
        return spyApi.logout();
    }

    @Override
    public Single<SelfModel> getSelfInfo() {
        return spyApi.getSelfInfo()
                .doOnSuccess(selfModel -> {
                    Timber.d("Store self info in DB");
                    databaseRepo.updateSelfInfo(selfModel);
                });
    }

    @Override
    public Single<TopOfLikes> getSelfTopLikes(int topCount) {
        return spyApi.getTopLikes(topCount)
                .doOnSuccess(topOfLikes -> {
                    Timber.d("Store topLikes in DB");
                    databaseRepo.storeTopLikes(topOfLikes);
                });
    }
}
