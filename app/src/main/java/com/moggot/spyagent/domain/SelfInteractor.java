package com.moggot.spyagent.domain;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.moggot.spyagent.data.model.SelfResponseModel;
import com.moggot.spyagent.data.model.TopOfLikes;
import com.moggot.spyagent.data.repository.DataRepoImpl;

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
    public Single<SelfResponseModel> getSelfInfo() {
        return dataRepo.getSelfInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @WorkerThread
    public Single<TopOfLikes> getSelfTopLikes(int topCount) {
        return dataRepo.getSelfTopLikes(topCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
