package com.zick.nsm.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zick.nsm.di.component.AppComponent;
import com.zick.nsm.presenter.BasePresenter;
import com.zick.nsm.ui.AppApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/3/6.
 */

public abstract class BaseFragment<T extends BasePresenter>  extends Fragment {

    private Unbinder mUnbinder;

    protected AppApplication mApplication;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = createView(inflater,container,savedInstanceState);
        ButterKnife.bind(this,view);
        mApplication = AppApplication.get(getActivity().getApplicationContext());
        //setupActivityAppComponent(mApplication.getDaggerAppComponent());
        init(mApplication.getDaggerAppComponent());
        return view;
    }

    public abstract View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    public abstract void init(AppComponent appComponent);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder !=null){
            mUnbinder.unbind();
        }
    }
}
