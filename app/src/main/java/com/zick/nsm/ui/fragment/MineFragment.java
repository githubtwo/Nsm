package com.zick.nsm.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zick.nsm.R;
import com.zick.nsm.common.rx.ErrorHandlerSubscriber;
import com.zick.nsm.common.rx.RxHttpReponseCompat;
import com.zick.nsm.di.component.AppComponent;
import com.zick.nsm.entity.Basebean;
import com.zick.nsm.entity.User;
import com.zick.nsm.ui.activity.LoginActivity;
import com.zick.nsm.ui.activity.MineActivity;
import com.zick.nsm.ui.activity.SettleActivity;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/10.
 */

public class MineFragment extends BaseFragment {


    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.collapsing_toolbar_layout)
    AppBarLayout mCollapsingToolbarLayout;
    @BindView(R.id.layout_user)
    LinearLayout mLayoutUser;
    @BindView(R.id.txt_login)
    TextView mTxtLogin;
    @BindView(R.id.img_head)
    CircleImageView mImgHead;
    private AppComponent mAppComponent;


    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void init(AppComponent appComponent) {
        mAppComponent = appComponent;
        refreshView(appComponent);
        mLayoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });


    }
    //刷新数据
    private void refreshView(final AppComponent appComponent) {

        initUser(appComponent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //initUser(appComponent);
                Log.e("refresh", "refreshing");
                initUser(appComponent);
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

    }

    private void initUser(AppComponent appComponent) {
        appComponent.provideApiService().getUserInfo()
                .compose(RxHttpReponseCompat.<Basebean<User>>compatOrigin())
                .subscribe(new ErrorHandlerSubscriber<Basebean<User>>() {
                    @Override
                    public void onNext(Basebean<User> userBasebean) {
                        if (userBasebean.getStatus() == 0) {
                            mTxtLogin.setText(userBasebean.getData().getUsername());
                            mLayoutUser.setEnabled(false);
                            if(userBasebean.getData().getImg() != null){
                                Glide
                                        .with(getActivity().getApplicationContext())
                                        .load("http://47.93.243.239:8080/mmall//picture/image?img=" + userBasebean.getData().getImg())
                                        .into(mImgHead);
                            }
                            mImgHead.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(getActivity(), MineActivity.class));
                                }
                            });
                        }else{
                            mImgHead.setEnabled(false);
                        }
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        initUser(mAppComponent);
    }
}
