package com.shreyasbhondve.starwarsapp.di.module;

import android.app.Application;
import android.content.Context;
import com.shreyasbhondve.starwarsapp.di.qualifier.ApplicationContext;
import com.shreyasbhondve.starwarsapp.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;


    private final Application mApplication;

    public ContextModule(Context context,Application app) {
        this.context = context;
        mApplication = app;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }

}
