package com.beginner2winner.orange.app.dagger.module;

import android.app.Application;
import android.content.Context;

import com.beginner2winner.orange.app.dagger.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2 Module for whole application
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

@Module
public class AppModule {

    private final Context context;

    public AppModule(Application application) {
        this.context = application.getApplicationContext();
    }

    @AppScope
    @Provides
    public Context context() {
        return this.context;
    }
}
