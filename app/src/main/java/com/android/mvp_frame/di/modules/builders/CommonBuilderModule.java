package com.android.mvp_frame.di.modules.builders;

import com.android.mvp_frame.di.ActivityScope;
import com.android.mvp_frame.ui.module.home.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 公共组件、服务和广播注册表
 */
@Module
public abstract class CommonBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivityInject();

}
