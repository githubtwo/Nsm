package com.zick.nsm.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zick.nsm.R;
import com.zick.nsm.common.rx.ErrorHandlerSubscriber;
import com.zick.nsm.common.rx.RxHttpReponseCompat;
import com.zick.nsm.di.component.AppComponent;
import com.zick.nsm.entity.Basebean;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/29.
 */

public class SettleActivity extends BaseActivity {

    @BindView(R.id.layout_finish)
    LinearLayout mLayoutFinish;
    @BindView(R.id.layout_connect_us)
    LinearLayout mLayoutConnectUs;
    @BindView(R.id.layout_about)
    LinearLayout mLayoutAbout;
    @BindView(R.id.btn_exit)
    Button mBtnExit;

    @Override
    public int setLayout() {
        return R.layout.activity_settle;
    }

    @Override
    public void init(final AppComponent appComponent) {
        mLayoutFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appComponent.provideApiService().logout()
                        .compose(RxHttpReponseCompat.<Basebean<String>>compatOrigin())
                        .subscribe(new ErrorHandlerSubscriber<Basebean<String>>() {
                            @Override
                            public void onNext(Basebean<String> stringBasebean) {
                                if(stringBasebean.getStatus() == 0){
                                    startActivity(new Intent(SettleActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }


    @Override
    public void setupActivityAppComponent(final AppComponent appComponent) {


    }


}
