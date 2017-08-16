package com.zick.nsm.ui.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zick.nsm.R;
import com.zick.nsm.common.rx.ErrorHandlerSubscriber;
import com.zick.nsm.common.rx.RxHttpReponseCompat;
import com.zick.nsm.di.component.AppComponent;
import com.zick.nsm.entity.Basebean;
import com.zick.nsm.entity.MovieInfo;
import com.zick.nsm.ui.adapter.HomeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/10.
 */

public class HomeFragment extends BaseFragment {
/*    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;*/
  /*  @BindView(R.id.collapsing_toolbar_layout)
    AppBarLayout mCollapsingToolbarLayout;*/

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private HomeAdapter mHomeAdapter;

    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void init(AppComponent appComponent) {
        initMaoYan(appComponent);
       // refreshView(appComponent);

    }

    private void initMaoYan(AppComponent appComponent){

        appComponent.provideApiService().getMaoYanMovie()
                .compose(RxHttpReponseCompat.<Basebean<List<MovieInfo>>>compatOrigin())
                .subscribe(new ErrorHandlerSubscriber<Basebean<List<MovieInfo>>>() {
                    @Override
                    public void onNext(Basebean<List<MovieInfo>> listBasebean) {
                        Log.e("list", listBasebean.toString());
                        initRecyclerView(listBasebean.getData());
                    }
                });
    }

    private void initRecyclerView(List<MovieInfo> infos){
        mHomeAdapter = new HomeAdapter(getActivity(),infos);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.setAdapter(mHomeAdapter);

    }

    /*//刷新数据
    private void refreshView(final AppComponent appComponent) {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //initUser(appComponent);
                Log.e("refresh", "refreshing");
                initMaoYan(appComponent);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        //设置样式刷新显示的位置
        mSwipeRefreshLayout.setProgressViewOffset(true, -20, 100);
        // mSwipeRefreshLayout.setColorSchemeResources(R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        mCollapsingToolbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset >= 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else {
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }*/


}
