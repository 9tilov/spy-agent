package com.moggot.spyagent.presentation.self;

import android.support.annotation.NonNull;

import com.moggot.spyagent.domain.SelfInteractor;
import com.moggot.spyagent.presentation.common.BasePresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SelfPresenter extends BasePresenter<SelfView> {

    @NonNull
    private SelfInteractor selfInteractor;

    @Inject
    public SelfPresenter(@NonNull SelfInteractor selfInteractor) {
        this.selfInteractor = selfInteractor;
    }

    public void getSelfInfo() {
        selfInteractor.getSelfInfo()
                .subscribe(userResponseModel -> {});
    }
}
