package com.shreyasbhondve.starwarsapp.di.component;

import android.content.Context;
import com.shreyasbhondve.starwarsapp.MyApplication;
import com.shreyasbhondve.starwarsapp.di.module.ContextModule;
import com.shreyasbhondve.starwarsapp.di.module.RetrofitModule;
import com.shreyasbhondve.starwarsapp.di.qualifier.ApplicationContext;
import com.shreyasbhondve.starwarsapp.di.scopes.ApplicationScope;
import com.shreyasbhondve.starwarsapp.retrofit.APIInterface;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public void injectApplication(MyApplication myApplication);

    @ApplicationContext
    public Context getContext();


    public APIInterface getApiInterface();



}
