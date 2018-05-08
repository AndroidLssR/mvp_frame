package com.android.mvp_frame.ui.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import org.greenrobot.eventbus.EventBus;

import javax.annotation.Nonnull;

/**
 * EventBus 消息订阅生命周期管理
 */
public class EventBusLifecycle implements LifecycleObserver {

    private Object context;

    public EventBusLifecycle(@Nonnull Object context) {
        this.context = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void register() {
        EventBus.getDefault().register(context);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void unregister() {
        EventBus.getDefault().unregister(context);
    }

}
