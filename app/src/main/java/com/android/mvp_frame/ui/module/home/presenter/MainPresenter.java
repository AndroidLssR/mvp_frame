package com.android.mvp_frame.ui.module.home.presenter;


import com.android.mvp_frame.data.repository.GankIoRepository;
import com.android.mvp_frame.di.ActivityScope;
import com.android.mvp_frame.frame.BasePresenter;

import javax.inject.Inject;

@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    @Inject
    public MainPresenter() {
    }

    @Override
    public void sayHello() {
        getView().sayHello("hello world");
    }
}
