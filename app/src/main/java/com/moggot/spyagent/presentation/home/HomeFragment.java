package com.moggot.spyagent.presentation.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moggot.spyagent.R;
import com.moggot.spyagent.data.model.SelfResponseModel;
import com.moggot.spyagent.di.Injector;
import com.moggot.spyagent.presentation.common.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements HomeView {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.iv_self_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_self_name)
    TextView tvName;
    @BindView(R.id.tv_self_surname)
    TextView tvSurname;

    @Inject
    HomePresenter presenter;

    public HomeFragment() {
        Injector.getUserComponent().inject(this);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onAttach(this);
//        presenter.getSelfInfo();
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public void showSelfInfo(SelfResponseModel selfModel) {
        tvName.setText(selfModel.getFirstName());
        tvSurname.setText(selfModel.getLastName());
        Glide.with(this).load(selfModel.getPhotoUrl()).into(ivPhoto);
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
