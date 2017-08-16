package com.zick.nsm.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/7/3.
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application){

        this.mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication(){

        return mApplication;
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }

}
