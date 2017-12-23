package com.moggot.spyagent.di.module;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.api.SpyApi;
import com.moggot.spyagent.data.repository.network.Authtorization;
import com.moggot.spyagent.data.repository.preference.PreferenceRepo;
import com.moggot.spyagent.di.scope.LoginScope;
import com.moggot.spyagent.domain.AuthInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @LoginScope
    Authtorization provideAuthtorization(SpyApi spyApi, PreferenceRepo preferenceRepo) {
        return new Authtorization(spyApi, preferenceRepo);
    }

    @Provides
    @LoginScope
    AuthInteractor provideAuthInteractor(@NonNull Authtorization authtorization) {
        return new AuthInteractor(authtorization);
    }
}
