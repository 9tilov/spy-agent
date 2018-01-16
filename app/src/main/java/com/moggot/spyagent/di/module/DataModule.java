package com.moggot.spyagent.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.moggot.spyagent.data.local.UserDao;
import com.moggot.spyagent.data.local.UserDatabase;
import com.moggot.spyagent.data.repository.local.DatabaseRepo;
import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences("storage", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    PreferenceRepo providePreferences(SharedPreferences sharedPreferences) {
        return new PreferenceRepo(sharedPreferences);
    }

    @Provides
    @Singleton
    DatabaseRepo provideDatabaseRepo(UserDao userDao, PreferenceRepo preferenceRepo, Executor executor) {
        return new DatabaseRepo(userDao, preferenceRepo, executor);
    }

    @Provides
    @Singleton
    UserDao provideUserDao(UserDatabase userDatabase) {
        return userDatabase.userDao();
    }

    @Provides
    @Singleton
    UserDatabase provideUserDatabase(Application application) {
        return Room
                .databaseBuilder(application.getApplicationContext(), UserDatabase.class, "user.db")
                .build();
    }
}
