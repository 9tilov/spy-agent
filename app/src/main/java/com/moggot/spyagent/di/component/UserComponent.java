package com.moggot.spyagent.di.component;

import com.moggot.spyagent.di.module.UserModule;
import com.moggot.spyagent.di.scope.UserScope;
import com.moggot.spyagent.presentation.favorites.FavoritesFragment;
import com.moggot.spyagent.presentation.home.HomeFragment;
import com.moggot.spyagent.presentation.profile.ProfileFragment;

import dagger.Subcomponent;

@UserScope
@Subcomponent(modules = {UserModule.class})
public interface UserComponent {

    void inject(HomeFragment fragment);

    void inject(FavoritesFragment fragment);

    void inject(ProfileFragment fragment);
}
