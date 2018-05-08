package com.android.mvp_frame.reactivex;


import com.android.mvp_frame.model.event.NetworkThrowableEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.CancellationException;

import io.reactivex.observers.DisposableSingleObserver;

public class DefaultDisposableSingleObserver<T> extends DisposableSingleObserver<T> {
    @Override
    public void onSuccess(T t) {
        // no-op by default
    }

    @Override
    public void onError(Throwable e) {
        if (!(e instanceof CancellationException)) {
            EventBus.getDefault().post(new NetworkThrowableEvent(e));
        }
    }

    public void onError(Throwable e, boolean toast) {
        if (!(e instanceof CancellationException)) {
            EventBus.getDefault().post(new NetworkThrowableEvent(e, toast));
        }
    }
}
