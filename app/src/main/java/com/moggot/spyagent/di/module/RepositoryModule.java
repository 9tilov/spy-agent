package com.moggot.spyagent.di.module;

import com.moggot.spyagent.data.repository.UserRepository;
import com.moggot.spyagent.data.repository.local.DatabaseRepo;
import com.moggot.spyagent.data.repository.network.NetworkRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    UserRepository provideUserRepo(DatabaseRepo databaseRepo, NetworkRepo networkRepo) {
        return new UserRepository(databaseRepo, networkRepo);
    }

}
