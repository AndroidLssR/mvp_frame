package com.android.mvp_frame.di.modules.provider;


import com.android.mvp_frame.data.api.GankIoService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ServiceModule {

    @Singleton
    @Provides
    public GankIoService provideCommonService(Retrofit retrofit) {
        return retrofit.create(GankIoService.class);
    }

}
