package com.shreyasbhondve.starwarsapp.di.component;

import com.shreyasbhondve.starwarsapp.di.module.AdapterModule;
import com.shreyasbhondve.starwarsapp.di.module.MainActivityContextModule;
import com.shreyasbhondve.starwarsapp.di.scopes.ActivityScope;
import com.shreyasbhondve.starwarsapp.ui.MainActivity;

import dagger.Component;


@ActivityScope
@Component(modules = {AdapterModule.class, MainActivityContextModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

//    @ActivityContext
//    Context getContext();


    void injectMainActivity(MainActivity mainActivity);
}
