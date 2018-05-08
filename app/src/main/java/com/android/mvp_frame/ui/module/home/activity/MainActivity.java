package com.android.mvp_frame.ui.module.home.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.mvp_frame.R;
import com.android.mvp_frame.frame.FrameActivity;
import com.android.mvp_frame.frame.Presenter;
import com.android.mvp_frame.ui.module.home.presenter.MainContract;
import com.android.mvp_frame.ui.module.home.presenter.MainPresenter;

import javax.inject.Inject;

import butterknife.OnClick;

public class MainActivity extends FrameActivity implements MainContract.View{

    @Presenter
    @Inject
    MainPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.btn_say_hello)
    void sayHello(){
        mPresenter.sayHello();
    }

    @Override
    public void sayHello(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
