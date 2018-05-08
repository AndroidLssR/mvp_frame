package com.android.mvp_frame.utils;


import com.android.mvp_frame.frame.BasePresenter;
import com.android.mvp_frame.frame.FrameActivity;
import com.android.mvp_frame.frame.FrameFragment;
import com.android.mvp_frame.frame.Presenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;


public class PresenterCompat {

    private static void process(Class clazz, ProcessRunnable runnable) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Presenter.class) != null) {
                runnable.run(field);
            }
        }
    }

    public static void inject(@Nonnull final FrameActivity activity) {
        process(activity.getClass(), field -> {
            try {
                field.setAccessible(true);
                Object o = field.get(activity);
                if (o == null) {
                    throw new NullPointerException(field.getName() + " is null");
                }
                if (o instanceof BasePresenter) {
                    BasePresenter presenter = ((BasePresenter) o);
                    registerPresenter(activity, presenter);
                } else {
                    throw new IllegalArgumentException("This filed must extends BasePresenter");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static void inject(@Nonnull final FrameFragment fragment) {
        process(fragment.getClass(), field -> {
            try {
                field.setAccessible(true);
                Object o = field.get(fragment);
                if (o == null) {
                    throw new NullPointerException(field.getName() + "is null");
                }
                if (o instanceof BasePresenter) {
                    BasePresenter presenter = ((BasePresenter) o);
                    registerPresenter(fragment, presenter);
                } else {
                    throw new IllegalArgumentException("This filed must extends BasePresenter");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }


    public static List<BasePresenter> getPresenters(Object object) {
        List<BasePresenter> basePresenters = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Presenter.class) != null) {
                field.setAccessible(true);
                try {
                    Object o = field.get(object);
                    if (o != null && o instanceof BasePresenter) {
                        BasePresenter presenter = ((BasePresenter) o);
                        basePresenters.add(presenter);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return basePresenters;
    }

    private static void registerPresenter(FrameActivity activity, BasePresenter presenter) {
        presenter.attachView(activity);
        presenter.setIntent(activity.getIntent());
        presenter.setApplicationContext(activity.getApplicationContext());
//        activity.getLifecycle().addObserver(presenter);
    }

    private static void registerPresenter(FrameFragment fragment, BasePresenter presenter) {
        presenter.attachView(fragment);
        presenter.setArguments(fragment.getArguments());
        presenter.setApplicationContext(fragment.getContext().getApplicationContext());
//        fragment.getLifecycle().addObserver(presenter);
    }

    interface ProcessRunnable {
        void run(Field field);
    }

}
