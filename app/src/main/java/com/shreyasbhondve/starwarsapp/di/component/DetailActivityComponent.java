package com.shreyasbhondve.starwarsapp.di.component;

import com.shreyasbhondve.starwarsapp.di.scopes.ActivityScope;
import com.shreyasbhondve.starwarsapp.ui.DetailActivity;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
@ActivityScope
public interface DetailActivityComponent {

    void inject(DetailActivity detailActivity);
}
