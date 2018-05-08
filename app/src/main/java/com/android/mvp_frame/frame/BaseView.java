package com.android.mvp_frame.frame;

import android.arch.lifecycle.Lifecycle;

import com.trello.rxlifecycle2.LifecycleProvider;

public interface BaseView {

    LifecycleProvider<Lifecycle.Event> getLifecycleProvider();

    void showProgressBar(CharSequence message);

    void showProgressBar(CharSequence message, boolean cancelable);

    void dismissProgressBar();

    void showToast(CharSequence message);

}
