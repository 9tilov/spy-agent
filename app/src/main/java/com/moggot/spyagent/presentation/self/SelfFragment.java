package com.moggot.spyagent.presentation.self;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moggot.spyagent.R;
import com.moggot.spyagent.presentation.common.BaseFragment;

public class SelfFragment extends BaseFragment {

    private static final String TAG = "SelfFragment";

    public SelfFragment() {
    }

    public static SelfFragment newInstance() {
        return new SelfFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.self_fragment, container, false);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
