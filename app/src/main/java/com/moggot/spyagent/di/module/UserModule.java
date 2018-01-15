package com.moggot.spyagent.di.module;

import android.support.annotation.NonNull;

import com.moggot.spyagent.data.repository.DataRepoImpl;
import com.moggot.spyagent.data.repository.UserRepository;
import com.moggot.spyagent.di.scope.UserScope;
import com.moggot.spyagent.domain.SelfInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    @UserScope
    SelfInteractor provideSelfInteractor(@NonNull UserRepository userRepository) {
        return new SelfInteractor(userRepository);
    }
}
