package com.moggot.spyagent.domain;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.moggot.spyagent.data.model.LoginResponse;
import com.moggot.spyagent.data.model.SelfModel;
import com.moggot.spyagent.data.repository.network.Authorization;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AuthInteractor {

    @NonNull
    private Authorization authorization;

    public AuthInteractor(@NonNull Authorization authorization) {
        this.authorization = authorization;
    }

    @WorkerThread
    public Single<LoginResponse> login(SelfModel selfModel) {
        return authorization.login(selfModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @WorkerThread
    public Single<LoginResponse> logout() {
        return authorization.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
