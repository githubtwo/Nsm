package com.zick.nsm.di.component;

import android.app.Activity;
import android.app.Application;

import com.zick.nsm.di.module.AppModule;
import com.zick.nsm.di.module.HttpModule;
import com.zick.nsm.http.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/7/3.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    //在AppComponent中没有注入，只通过provideApiService往HttpModule.class中找实例
    public ApiService provideApiService();

    public void inject(Activity activity);
    public Application getApplication();

}
