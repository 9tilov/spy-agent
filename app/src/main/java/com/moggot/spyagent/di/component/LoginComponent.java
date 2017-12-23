package com.moggot.spyagent.di.component;

import com.moggot.spyagent.di.module.LoginModule;
import com.moggot.spyagent.di.scope.LoginScope;
import com.moggot.spyagent.presentation.login.LoginActivity;

import dagger.Subcomponent;

@LoginScope
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivity loginActivity);
}
