package com.android.mvp_frame.model.event;

public class NetworkThrowableEvent {

    private Throwable throwable;

    private boolean toast = true;

    public NetworkThrowableEvent(Throwable throwable) {
        this.throwable = throwable;
    }

    public NetworkThrowableEvent(Throwable throwable, boolean toast) {
        this.throwable = throwable;
        this.toast = toast;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public boolean isToast() {
        return toast;
    }

    public void setToast(boolean toast) {
        this.toast = toast;
    }
}
