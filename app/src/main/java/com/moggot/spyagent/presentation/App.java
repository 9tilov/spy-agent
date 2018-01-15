package com.moggot.spyagent.presentation;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.moggot.spyagent.BuildConfig;
import com.moggot.spyagent.di.component.AppComponent;
import com.moggot.spyagent.di.component.DaggerAppComponent;
import com.moggot.spyagent.di.module.AppModule;
import com.moggot.spyagent.di.module.NetModule;
import com.vk.sdk.VKSdk;

import timber.log.Timber;

public class App extends Application {

    private static App instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setInstance(this);
        // Dagger%COMPONENT_NAME%
        appComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule())
                .build();
        appComponent.inject(this);

        Stetho.initializeWithDefaults(this);

        VKSdk.initialize(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private static void setInstance(App instance) {
        App.instance = instance;
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void releaseAppComponent() {
        appComponent = null;
    }
}
