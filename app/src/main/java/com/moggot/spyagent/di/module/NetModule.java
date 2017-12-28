package com.moggot.spyagent.di.module;

import com.moggot.spyagent.data.api.SpyApi;
import com.moggot.spyagent.data.network.AuthInterseptor;
import com.moggot.spyagent.data.network.CommonServerModel;
import com.moggot.spyagent.data.repository.DataRepoImpl;
import com.moggot.spyagent.data.repository.network.NetworkRepo;
import com.moggot.spyagent.data.repository.preference.PreferenceRepo;

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
    AuthInterseptor provideAuthInterseptor(CommonServerModel commonServerModel) {
        return new AuthInterseptor(commonServerModel);
    }

    @Provides
    @Singleton
    CommonServerModel provideCommonServerModel(PreferenceRepo preferenceRepo) {
        return new CommonServerModel(preferenceRepo);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, AuthInterseptor authInterseptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterseptor)
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
    DataRepoImpl provideNetworkRepo(SpyApi spyApi, PreferenceRepo preferenceRepo) {
        return new NetworkRepo(spyApi, preferenceRepo);
    }
}