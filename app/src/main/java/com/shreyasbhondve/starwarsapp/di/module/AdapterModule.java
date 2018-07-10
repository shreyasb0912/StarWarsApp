package com.shreyasbhondve.starwarsapp.di.module;


import com.shreyasbhondve.starwarsapp.adapter.RecyclerViewAdapter;
import com.shreyasbhondve.starwarsapp.di.scopes.ActivityScope;
import com.shreyasbhondve.starwarsapp.ui.MainActivity;

import dagger.Module;
import dagger.Provides;


@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getProductList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }

}
