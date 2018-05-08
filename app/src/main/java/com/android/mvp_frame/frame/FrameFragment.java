package com.android.mvp_frame.frame;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.android.mvp_frame.utils.PresenterCompat;
import com.android.mvp_frame.utils.SafetyHandler;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.internal.Beta;

@Beta
public abstract class FrameFragment extends Fragment implements HasFragmentInjector, SafetyHandler.Delegate, BaseView {

    private final LifecycleProvider<Lifecycle.Event> provider
            = AndroidLifecycle.createLifecycleProvider(this);
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> childFragmentInjector;

    private Unbinder unbinder;

    private final SafetyHandler mSafetyHandler = SafetyHandler.create(this);

    @Override
    public void onAttach(Context context) {
        try {
            AndroidSupportInjection.inject(this);
        } catch (Exception ignored) {
        }
        super.onAttach(context);
        PresenterCompat.inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        // 存在presenter onCreate调用，保证View已经初始化
        List<BasePresenter> presenters = PresenterCompat.getPresenters(this);
        for (BasePresenter presenter : presenters) {
            getLifecycle().addObserver(presenter);
        }
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return childFragmentInjector;
    }

    @Override
    public LifecycleProvider<Lifecycle.Event> getLifecycleProvider() {
        return provider;
    }

    @Override
    public void showProgressBar(CharSequence message) {
        showProgressBar(message, true);
    }

    @Override
    public void showProgressBar(CharSequence message, boolean cancelable) {
        if (getActivity() instanceof FrameActivity) {
            ((FrameActivity) getActivity()).showProgressBar(message, cancelable);
        }
    }


    @Override
    public void dismissProgressBar() {
        if (getActivity() instanceof FrameActivity) {
            ((FrameActivity) getActivity()).dismissProgressBar();
        }
    }

    public SafetyHandler getSafetyHandler() {
        return this.mSafetyHandler;
    }

    @Override
    public void onReceivedHandlerMessage(Message message) {
    }

    @Override
    public void showToast(CharSequence message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    public boolean onBackPressed() {
        return false;
    }

}
