package com.zick.nsm.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zick.nsm.R;
import com.zick.nsm.entity.MovieInfo;
import com.zick.nsm.ui.activity.MovieDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/11.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<MovieInfo> mMovieInfoList;
    private HomeFirstAdapter mHomeFirstAdapter;

    public HomeAdapter(Context context, List<MovieInfo> movieInfos) {
        this.mContext = context;
        this.mMovieInfoList = movieInfos;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new FirstHolder(mInflater.inflate(R.layout.template_home_first, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            FirstHolder firstHolder = (FirstHolder) holder;
            mHomeFirstAdapter = new HomeFirstAdapter(mContext, mMovieInfoList);
            firstHolder.mRecyclerFirst.setLayoutManager(new LinearLayoutManager(mContext));
            firstHolder.mRecyclerFirst.setAdapter(mHomeFirstAdapter);
            mHomeFirstAdapter.setOnItemClickListener(new HomeFirstAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    MovieInfo movieInfo = mHomeFirstAdapter.getMovieInfo(position);
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    intent.putExtra("movieDetail",movieInfo);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class FirstHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_first)
        RecyclerView mRecyclerFirst;
        public FirstHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
