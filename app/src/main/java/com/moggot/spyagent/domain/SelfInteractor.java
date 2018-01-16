package com.moggot.spyagent.domain;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.moggot.spyagent.data.model.SelfModel;
import com.moggot.spyagent.data.model.TopOfLikes;
import com.moggot.spyagent.data.repository.UserRepository;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SelfInteractor {

    @NonNull
    private UserRepository userRepository;

    public SelfInteractor(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @WorkerThread
    public Flowable<SelfModel> getSelfInfo() {
        return userRepository.getSelfInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @WorkerThread
    public Single<TopOfLikes> getSelfTopLikes(int topCount) {
        return userRepository.getSelfTopLikes(topCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
