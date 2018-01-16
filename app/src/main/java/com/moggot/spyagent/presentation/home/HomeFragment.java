package com.moggot.spyagent.presentation.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.moggot.spyagent.R;
import com.moggot.spyagent.data.model.SelfModel;
import com.moggot.spyagent.data.model.TopOfLikes;
import com.moggot.spyagent.data.model.UserInfo;
import com.moggot.spyagent.di.Injector;
import com.moggot.spyagent.presentation.common.BaseFragment;
import com.moggot.spyagent.presentation.home.adapter.FriendAdapter;

import java.util.List;

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
    @BindView(R.id.rv_top_friends)
    RecyclerView rvTopFriends;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getInfo();
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public void showSelfInfo(SelfModel selfModel) {
        tvName.setText(selfModel.getFirstName());
        tvSurname.setText(selfModel.getLastName());
        Glide.with(this)
                .load(selfModel.getPhotoUrl())
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(ivPhoto);
    }

    @Override
    public void showListTopLikes(TopOfLikes topOfLikes) {
        initRecycler(topOfLikes.getUsersInfo());
    }

    private void initRecycler(List<UserInfo> friendList) {
        rvTopFriends.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTopFriends.setLayoutManager(mLayoutManager);

        // Disabled nested scrolling since Parent scrollview will scroll the content.
        rvTopFriends.setNestedScrollingEnabled(false);

        // specify an adapter (see also next example)
        FriendAdapter mAdapter = new FriendAdapter(friendList);
        rvTopFriends.setAdapter(mAdapter);
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
