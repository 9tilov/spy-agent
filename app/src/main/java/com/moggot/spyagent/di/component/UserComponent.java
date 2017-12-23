package com.moggot.spyagent.di.component;

import com.moggot.spyagent.di.module.UserModule;
import com.moggot.spyagent.di.scope.UserScope;

import dagger.Subcomponent;

@UserScope
@Subcomponent(modules = {UserModule.class})
public interface UserComponent {
}
