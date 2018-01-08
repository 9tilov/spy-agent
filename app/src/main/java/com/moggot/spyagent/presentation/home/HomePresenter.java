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
    HomePresenter(@NonNull SelfInteractor selfInteractor) {
        this.selfInteractor = selfInteractor;
    }

    void getInfo() {
        unSubscribeOnDetach(selfInteractor.getSelfInfo()
                .doOnSuccess(selfResponseModel -> {
                    getViewOrThrow().showSelfInfo(selfResponseModel);
                    selfInteractor.getSelfTopLikes(5)
                            .subscribe(topOfLikes -> getViewOrThrow().showListTopLikes(topOfLikes));
                })
                .subscribe());
    }
}
