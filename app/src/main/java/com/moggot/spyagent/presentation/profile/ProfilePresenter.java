package com.moggot.spyagent.presentation.profile;

import com.moggot.spyagent.di.scope.UserScope;
import com.moggot.spyagent.presentation.common.BasePresenter;

import javax.inject.Inject;

@UserScope
public class ProfilePresenter extends BasePresenter<ProfileView> {

    @Inject
    public ProfilePresenter(){}
}
