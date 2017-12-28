package com.moggot.spyagent.presentation.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moggot.spyagent.R;
import com.moggot.spyagent.di.Injector;
import com.moggot.spyagent.presentation.common.BaseFragment;

import javax.inject.Inject;

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

    @Override
    public void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void showNetworkError() {

    }
}
