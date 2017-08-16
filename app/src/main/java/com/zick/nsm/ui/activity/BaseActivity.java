package com.zick.nsm.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zick.nsm.di.component.AppComponent;
import com.zick.nsm.presenter.BasePresenter;
import com.zick.nsm.ui.AppApplication;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/3.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private Unbinder mUnbinder;

    protected AppApplication mApplication;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setLayout());
        mUnbinder = ButterKnife.bind(this);
        //mApplication = AppApplication.get(getApplication());

        mApplication = AppApplication.get(getApplicationContext());
        setupActivityAppComponent(mApplication.getDaggerAppComponent());
        init(mApplication.getDaggerAppComponent());
    }

    public abstract int setLayout();

    public abstract void init(AppComponent appComponent);

    public abstract void setupActivityAppComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }
}
