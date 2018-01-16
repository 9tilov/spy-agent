package com.moggot.spyagent.presentation.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moggot.spyagent.R;
import com.moggot.spyagent.di.Injector;
import com.moggot.spyagent.presentation.common.BaseFragment;

import javax.inject.Inject;

public class FavoritesFragment extends BaseFragment implements FavoritesView {

    private static final String TAG = "FavoritesFragment";

    @Inject
    FavoritesPresenter presenter;

    public FavoritesFragment() {
        Injector.getUserComponent().inject(this);
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
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
    public void showNetworkError() {

    }

    @Override
    public void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
