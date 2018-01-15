package com.moggot.spyagent.presentation.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.moggot.spyagent.R;
import com.moggot.spyagent.presentation.common.BaseFragment;
import com.moggot.spyagent.presentation.favorites.FavoritesFragment;
import com.moggot.spyagent.presentation.home.HomeFragment;
import com.moggot.spyagent.presentation.profile.ProfileFragment;
import com.moggot.spyagent.presentation.ui.widget.MainToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    MainToolbar toolbar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private int navigationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle(getString(R.string.app_name));
        if (savedInstanceState != null) {
            bottomNavigationView.setSelectedItemId(navigationId);
        } else {
            showFragment(bottomNavigationView.getSelectedItemId());
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> showFragment(item.getItemId()));
    }

    private boolean showFragment(@IdRes int itemId) {
        switch (itemId) {
            case R.id.action_home:
                toolbar.setTitle(getString(R.string.app_name));
                loadFragment(HomeFragment.newInstance());
                return true;
            case R.id.action_favorites:
                toolbar.setTitle(getString(R.string.favorites));
                loadFragment(FavoritesFragment.newInstance());
                return true;
            case R.id.action_profile:
                toolbar.setTitle(getString(R.string.profile));
                loadFragment(ProfileFragment.newInstance());
                return true;
            default:
                toolbar.setTitle(getString(R.string.app_name));
                loadFragment(HomeFragment.newInstance());
                return true;
        }
    }

    private void loadFragment(BaseFragment fragment) {
        Fragment cachedFragment = getSupportFragmentManager().findFragmentByTag(fragment.getFragmentTag());
        if (cachedFragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, fragment, fragment.getFragmentTag())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(navigationId);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        navigationId = bottomNavigationView.getSelectedItemId();
    }
}
