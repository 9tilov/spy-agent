package com.moggot.spyagent.presentation.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moggot.spyagent.R;
import com.moggot.spyagent.di.Injector;
import com.moggot.spyagent.presentation.common.BaseFragment;
import com.moggot.spyagent.presentation.login.LoginActivity;
import com.vk.sdk.VKSdk;

import javax.inject.Inject;

import butterknife.OnClick;

public class ProfileFragment extends BaseFragment implements ProfileView {

    private static final String TAG = "ProfileFragment";

    @Inject
    ProfilePresenter presenter;

    public ProfileFragment() {
        Injector.getUserComponent().inject(this);
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onAttach(this);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @OnClick(R.id.tv_logout)
    public void exit() {
        VKSdk.logout();
        if (!VKSdk.isLoggedIn()) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void showNetworkError() {

    }
}
