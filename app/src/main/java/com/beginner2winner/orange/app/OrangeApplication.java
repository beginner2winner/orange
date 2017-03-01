package com.beginner2winner.orange.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.beginner2winner.orange.app.dagger.AppComponent;
import com.beginner2winner.orange.app.dagger.DaggerAppComponent;
import com.beginner2winner.orange.app.dagger.module.AppModule;

/**
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class OrangeApplication extends Application {

    // Static accessors

    public static OrangeApplication get(Activity activity) {
        return (OrangeApplication) activity.getApplication();
    }

    public static OrangeApplication get(Service service) {
        return (OrangeApplication) service.getApplication();
    }

    private AppComponent appComponent;

    public AppComponent component() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }
}
