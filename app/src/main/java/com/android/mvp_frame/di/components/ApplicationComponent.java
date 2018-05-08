package com.android.mvp_frame.di.components;


import com.android.mvp_frame.App;
import com.android.mvp_frame.di.modules.ApplicationModule;
import com.android.mvp_frame.di.modules.builders.CommonBuilderModule;
import com.android.mvp_frame.di.modules.provider.ConfigModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ConfigModule.class,
        CommonBuilderModule.class,
})
public interface ApplicationComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
        abstract Builder configModule(ConfigModule configModule);

        @Override
        public void seedInstance(App instance) {
            configModule(new ConfigModule(instance));
        }
    }

}
