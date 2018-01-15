package com.moggot.spyagent.di.module;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.repository.local.DatabaseRepo;
import com.moggot.spyagent.data.repository.network.Authorization;
import com.moggot.spyagent.data.repository.network.NetworkRepo;
import com.moggot.spyagent.di.scope.LoginScope;
import com.moggot.spyagent.domain.AuthInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @LoginScope
    Authorization provideAuthtorization(NetworkRepo networkRepo, DatabaseRepo databaseRepo) {
        return new Authorization(networkRepo, databaseRepo);
    }

    @Provides
    @LoginScope
    AuthInteractor provideAuthInteractor(@NonNull Authorization authorization) {
        return new AuthInteractor(authorization);
    }
}
