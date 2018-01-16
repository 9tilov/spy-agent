package com.moggot.spyagent.data.repository.network;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.model.LoginResponse;
import com.moggot.spyagent.data.model.SelfModel;
import com.moggot.spyagent.data.repository.local.DatabaseRepo;
import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

import io.reactivex.Single;

public class Authorization {

    @NonNull
    private NetworkRepo networkRepo;
    @NonNull
    private DatabaseRepo databaseRepo;

    public Authorization(@NonNull NetworkRepo networkRepo, @NonNull DatabaseRepo databaseRepo) {
        this.networkRepo = networkRepo;
        this.databaseRepo = databaseRepo;
    }

    public Single<LoginResponse> login(SelfModel selfModel) {
        databaseRepo.saveCredentials(selfModel);
        return networkRepo.login(selfModel.getAccessToken());
    }

    public Single<LoginResponse> logout() {
        return networkRepo.logout();
    }
}
