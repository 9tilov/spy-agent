package com.moggot.spyagent.presentation.home;

import android.support.annotation.NonNull;

import com.moggot.spyagent.di.scope.UserScope;
import com.moggot.spyagent.domain.SelfInteractor;
import com.moggot.spyagent.presentation.common.BasePresenter;

import javax.inject.Inject;

@UserScope
public class HomePresenter extends BasePresenter<HomeView> {

    @NonNull
    private SelfInteractor selfInteractor;

    @Inject
    public HomePresenter(@NonNull SelfInteractor selfInteractor) {
        this.selfInteractor = selfInteractor;
    }

    public void getSelfInfo() {
        selfInteractor.getSelfInfo()
                .subscribe(userResponseModel -> getViewOrThrow().showSelfInfo(userResponseModel));
    }
}
