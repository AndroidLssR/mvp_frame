package com.android.mvp_frame.ui.module.home.activity

import android.os.Bundle
import android.widget.Toast

import com.android.mvp_frame.R
import com.android.mvp_frame.frame.FrameActivity
import com.android.mvp_frame.frame.Presenter
import com.android.mvp_frame.ui.module.home.presenter.MainContract
import com.android.mvp_frame.ui.module.home.presenter.MainPresenter

import javax.inject.Inject

import butterknife.OnClick

class KotlinActivity : FrameActivity(), MainContract.View {

    @Presenter
    @Inject
    internal var mPresenter: MainPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @OnClick(R.id.btn_say_hello)
    internal fun sayHello() {
        mPresenter!!.sayHello()
    }

    override fun sayHello(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
