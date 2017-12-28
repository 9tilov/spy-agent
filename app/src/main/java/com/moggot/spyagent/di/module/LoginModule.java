package com.moggot.spyagent.di.module;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.api.SpyApi;
import com.moggot.spyagent.data.network.CommonServerModel;
import com.moggot.spyagent.data.repository.network.Authorization;
import com.moggot.spyagent.data.repository.preference.PreferenceRepo;
import com.moggot.spyagent.di.scope.LoginScope;
import com.moggot.spyagent.domain.AuthInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @LoginScope
    Authorization provideAuthtorization(SpyApi spyApi, PreferenceRepo preferenceRepo, CommonServerModel commonServerModel) {
        return new Authorization(spyApi, preferenceRepo, commonServerModel);
    }

    @Provides
    @LoginScope
    AuthInteractor provideAuthInteractor(@NonNull Authorization authorization) {
        return new AuthInteractor(authorization);
    }
}
