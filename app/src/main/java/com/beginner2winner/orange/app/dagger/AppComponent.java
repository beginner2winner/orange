package com.beginner2winner.orange.app.dagger;

import android.content.Context;

import com.beginner2winner.orange.app.dagger.module.AppModule;
import com.beginner2winner.orange.app.dagger.module.NetworkModule;
import com.beginner2winner.orange.app.network.EndClothingNetwork;
import com.squareup.picasso.Picasso;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Dagger2 Component for whole application
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

@AppScope
@Component(modules = { AppModule.class , NetworkModule.class })
public interface AppComponent {

    Context context();

    OkHttpClient okhttpClient();

    EndClothingNetwork endClothingNetwork();

    Picasso picasso();

}
