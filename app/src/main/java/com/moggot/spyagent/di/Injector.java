package com.moggot.spyagent.di;

import com.moggot.spyagent.di.component.LoginComponent;
import com.moggot.spyagent.di.component.UserComponent;
import com.moggot.spyagent.di.module.LoginModule;
import com.moggot.spyagent.di.module.UserModule;
import com.moggot.spyagent.presentation.App;

public final class Injector {

    private static LoginComponent loginComponent = null;
    private static UserComponent userComponent = null;

    private Injector() {
        throw new IllegalStateException("This is injector class");
    }

    public static LoginComponent getLoginComponent() {
        if (loginComponent == null) {
            loginComponent = App.getInstance().getAppComponent().addLoginComponent(new LoginModule());
        }
        return loginComponent;
    }

    public static UserComponent getUserComponent() {
        if (userComponent == null) {
            userComponent = App.getInstance().getAppComponent().addUserComponent(new UserModule());
        }
        return userComponent;
    }

    public static void releaseUserComponent() {
        userComponent = null;
    }

    public static void releaseLoginComponent() {
        loginComponent = null;
    }
}
