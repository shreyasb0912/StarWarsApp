package com.shreyasbhondve.starwarsapp;

import android.app.Activity;
import android.app.Application;

import com.shreyasbhondve.starwarsapp.di.component.ApplicationComponent;
import com.shreyasbhondve.starwarsapp.di.component.DaggerApplicationComponent;
import com.shreyasbhondve.starwarsapp.di.module.ContextModule;
import com.shreyasbhondve.starwarsapp.ui.InternetConnectivityReceiver;

import javax.inject.Inject;


public class MyApplication extends Application {

    ApplicationComponent applicationComponent;
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this, (Application) getApplicationContext())).build();
//        applicationComponent  = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this))
//                .build();
        applicationComponent.injectApplication(this);
        mInstance = this;

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(InternetConnectivityReceiver.ConnectivityReceiverListener listener) {
        InternetConnectivityReceiver.connectivityReceiverListener = listener;
    }
}

