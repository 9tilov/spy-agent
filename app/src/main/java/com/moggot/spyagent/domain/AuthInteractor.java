package com.moggot.spyagent.domain;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.moggot.spyagent.data.model.LoginResponse;
import com.moggot.spyagent.data.repository.network.Authtorization;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AuthInteractor {

    @NonNull
    private Authtorization authtorization;

    public AuthInteractor(@NonNull Authtorization authtorization) {
        this.authtorization = authtorization;
    }

    @WorkerThread
    public Single<LoginResponse> login(String userId, String accessToken) {
        return authtorization.login(Long.parseLong(userId), accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @WorkerThread
    public Single<LoginResponse> logout() {
        return authtorization.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
