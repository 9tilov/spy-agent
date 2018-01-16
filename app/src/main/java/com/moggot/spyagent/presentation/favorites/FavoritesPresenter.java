package com.moggot.spyagent.presentation.favorites;

import com.moggot.spyagent.di.scope.UserScope;
import com.moggot.spyagent.presentation.common.BasePresenter;

import javax.inject.Inject;

@UserScope
public class FavoritesPresenter extends BasePresenter<FavoritesView> {

    @Inject
    public FavoritesPresenter() {
    }
}
