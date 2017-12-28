package com.moggot.spyagent.presentation.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends BaseView> {

    private T view;

    private final CompositeDisposable disposable = new CompositeDisposable();

    protected void unSubscribeOnDetach(Disposable... disposables) {
        disposable.addAll(disposables);
    }

    public void onAttach(T view) {
        this.view = view;
    }

    public void onDetach() {
        this.view = null;
    }

    public void networkError() {
        getViewOrThrow().showNetworkError();
    }

    @Nullable
    private T getView() {
        return view;
    }

    @NonNull
    public T getViewOrThrow() {
        final T view = getView();
        if (view == null) {
            throw new IllegalStateException("View is not attached");
        }
        return view;
    }
}
