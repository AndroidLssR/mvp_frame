package com.android.mvp_frame.ui.module.home.activity;

import android.os.Bundle;
import com.android.mvp_frame.R;
import com.android.mvp_frame.frame.FrameActivity;
import com.android.mvp_frame.frame.Presenter;
import com.android.mvp_frame.ui.module.home.presenter.MainContract;
import com.android.mvp_frame.ui.module.home.presenter.MainPresenter;

import javax.inject.Inject;

public class MainActivity extends FrameActivity implements MainContract.View{

    @Presenter
    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showHomeAsUp(false);
    }
}
