package com.moggot.spyagent.presentation.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moggot.spyagent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainToolbar extends RelativeLayout {

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.main_toolbar_exit)
    TextView tvExit;

    public MainToolbar(Context context) {
        this(context, null);
    }

    public MainToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.main_toolbar, this, true);
        ButterKnife.bind(this);
    }

    public void setNavigationButton() {
    }

    public void setTvTitle(CharSequence tvTitle) {
        this.tvTitle.setText(tvTitle);
    }

    public Toolbar getWrappedToolbar() {
        return toolbar;
    }
}
