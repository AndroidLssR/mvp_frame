package com.android.mvp_frame.reactivex;


import com.android.mvp_frame.model.event.NetworkThrowableEvent;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class DefaultDisposableObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(@NonNull T t) {
        // no-op by default
    }

    @Override
    public void onError(@NonNull Throwable e) {
        EventBus.getDefault().post(new NetworkThrowableEvent(e));
    }

    public void onError(@NonNull Throwable e, boolean toast) {
        EventBus.getDefault().post(new NetworkThrowableEvent(e, toast));
    }

    @Override
    public void onComplete() {
        // no-op by default
    }
}
