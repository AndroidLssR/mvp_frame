package com.android.mvp_frame.data.manager;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 配置管理器
 */
@Singleton
public class PrefManager {

    private SharedPreferences mSharedPreferences;

    @Inject
    public PrefManager(SharedPreferences preferences) {
        this.mSharedPreferences = preferences;
    }

}
