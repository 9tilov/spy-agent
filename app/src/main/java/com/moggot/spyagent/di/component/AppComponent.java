package com.moggot.spyagent.di.component;

import com.moggot.spyagent.di.module.AppModule;
import com.moggot.spyagent.di.module.DataModule;
import com.moggot.spyagent.di.module.LoginModule;
import com.moggot.spyagent.di.module.NetModule;
import com.moggot.spyagent.di.module.UserModule;
import com.moggot.spyagent.presentation.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        DataModule.class})
public interface AppComponent {

    LoginComponent addLoginComponent(LoginModule loginModule);
    UserComponent addUserComponent(UserModule userModule);

    void inject(App app);
}
