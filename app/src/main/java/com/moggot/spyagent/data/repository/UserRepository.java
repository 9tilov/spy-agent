package com.moggot.spyagent.data.repository;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.model.SelfModel;
import com.moggot.spyagent.data.model.TopOfLikes;
import com.moggot.spyagent.data.repository.local.DatabaseRepo;
import com.moggot.spyagent.data.repository.network.NetworkRepo;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class UserRepository {

    @NonNull
    private DatabaseRepo databaseRepo;
    @NonNull
    private NetworkRepo networkRepo;

    public UserRepository(@NonNull DatabaseRepo databaseRepo, @NonNull NetworkRepo networkRepo) {
        this.databaseRepo = databaseRepo;
        this.networkRepo = networkRepo;
    }

    public Flowable<SelfModel> getSelfInfo() {
        return Single.concat(databaseRepo.getSelfInfo(), networkRepo.getSelfInfo())
                .filter(selfModel -> selfModel != null)
                .doOnError(throwable -> networkRepo.getSelfInfo());
    }

    public Single<TopOfLikes> getSelfTopLikes(int topCount) {
        return Single.concat(networkRepo.getSelfTopLikes(topCount), databaseRepo.getSelfTopLikes(topCount)).firstOrError();
    }
}
