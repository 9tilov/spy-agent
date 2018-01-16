package com.moggot.spyagent.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.moggot.spyagent.data.network.api.SpyApi;
import com.moggot.spyagent.data.network.AuthInterseptor;
import com.moggot.spyagent.data.repository.local.DatabaseRepo;
import com.moggot.spyagent.data.repository.network.NetworkRepo;
import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> Timber.d(message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    AuthInterseptor provideAuthInterseptor(PreferenceRepo preferenceRepo) {
        return new AuthInterseptor(preferenceRepo);
    }

    @Provides
    @Singleton
    StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, AuthInterseptor authInterseptor, StethoInterceptor stethoInterceptor) {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(authInterseptor)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(stethoInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    @Provides
    @Singleton
    SpyApi provideSpyApi(Retrofit.Builder builder) {
        return builder
                .baseUrl(SpyApi.BASE_SPY_URL)
                .build()
                .create(SpyApi.class);
    }

    @Provides
    @Singleton
    NetworkRepo provideNetworkRepo(SpyApi spyApi, DatabaseRepo databaseRepo) {
        return new NetworkRepo(spyApi, databaseRepo);
    }

    @Provides
    @Singleton
    Executor provideExecutor() {
        return Executors.newFixedThreadPool(2);
    }
}
