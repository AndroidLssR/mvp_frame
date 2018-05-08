package com.android.mvp_frame.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

public class LayoutInflaterConvert implements LayoutInflater.Factory2 {

    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return onCreateView(name, context, attrs);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        if (ImageView.class.getSimpleName().equals(name)) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context, attrs);

            int resId = attrs.getAttributeResourceValue(ANDROID_SCHEMA, "src", 0);
            if (resId != 0) {
                simpleDraweeView.setImageResource(resId);
            }

            return simpleDraweeView;
        }

        return null;
    }
}
