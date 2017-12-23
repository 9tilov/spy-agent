package com.moggot.spyagent.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

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
}
