package com.moggot.spyagent.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.moggot.spyagent.R;
import com.moggot.spyagent.presentation.common.BaseFragment;
import com.moggot.spyagent.presentation.login.LoginActivity;
import com.moggot.spyagent.presentation.self.SelfFragment;
import com.moggot.spyagent.presentation.ui.widget.MainToolbar;
import com.vk.sdk.VKSdk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    MainToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SelfFragment fragment = SelfFragment.newInstance();
        setContentFragment(fragment, fragment.getFragmentTag());
    }

    @OnClick(R.id.main_toolbar_exit)
    public void exit() {
        VKSdk.logout();
        if (!VKSdk.isLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    private void setContentFragment(BaseFragment contentFragment, String tag) {
        if (getContentFragment(tag) == null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_container, contentFragment, tag);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    private BaseFragment getContentFragment(String tag) {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment instanceof BaseFragment) {
            return (BaseFragment) fragment;
        }
        return null;
    }
}
