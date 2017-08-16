package com.zick.nsm.ui;

import android.app.Application;
import android.content.Context;

import com.zick.nsm.di.component.AppComponent;
import com.zick.nsm.di.component.DaggerAppComponent;
import com.zick.nsm.di.module.AppModule;
import com.zick.nsm.di.module.HttpModule;

/**
 * Created by Administrator on 2017/8/10.
 */

public class AppApplication extends Application{

    AppComponent mAppComponent;

    public AppComponent getDaggerAppComponent(){

        return mAppComponent;
    }

    public static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();

    }
}
