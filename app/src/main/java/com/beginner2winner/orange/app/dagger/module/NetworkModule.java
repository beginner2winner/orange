package com.beginner2winner.orange.app.dagger.module;

import android.content.Context;

import com.beginner2winner.orange.app.Config;
import com.beginner2winner.orange.app.dagger.AppScope;
import com.beginner2winner.orange.app.network.EndClothingNetwork;
import com.beginner2winner.orange.app.network.model.EndClothingConverterFactory;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import timber.log.Timber;

/**
 * Dagger2 Module for Network Access
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

@Module
public class NetworkModule {

    @AppScope
    @Provides
    public Cache cache(Context context) {
        return new Cache(new File(context.getCacheDir(), Config.HTTP_CACHE_DIR),
                Config.HTTP_CACHE_SIZE);
    }

    @AppScope
    @Provides
    public EndClothingConverterFactory endClothingConverterFactory() {
        return new EndClothingConverterFactory();
    }

    @AppScope
    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }

    @AppScope
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                message -> Timber.d(message));
        return logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @AppScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, EndClothingConverterFactory endClothingConverterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Config.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(endClothingConverterFactory)
                .build();
    }

    @AppScope
    @Provides
    public EndClothingNetwork endClothingNetwork(Retrofit retrofit) {
        return retrofit.create(EndClothingNetwork.class);
    }

    @AppScope
    @Provides
    public Picasso picasso(Context context, OkHttpClient okHttpClient) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
    }
}
