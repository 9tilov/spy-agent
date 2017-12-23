package com.moggot.spyagent.domain;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.moggot.spyagent.data.model.UserResponseModel;
import com.moggot.spyagent.data.repository.DataRepoImpl;
import com.moggot.spyagent.data.repository.network.NetworkRepo;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SelfInteractor {

    @NonNull
    private DataRepoImpl dataRepo;

    public SelfInteractor(@NonNull DataRepoImpl dataRepo) {
        this.dataRepo = dataRepo;
    }

    @WorkerThread
    public Single<UserResponseModel> getSelfInfo() {
        return dataRepo.getSelfInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
