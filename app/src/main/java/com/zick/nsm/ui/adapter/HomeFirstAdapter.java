package com.zick.nsm.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zick.nsm.R;
import com.zick.nsm.entity.MovieInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/11.
 */

public class HomeFirstAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MovieInfo> mMovieInfoList;
    private OnItemClickListener mOnItemClickListener = null;

    public HomeFirstAdapter(Context context, List<MovieInfo> movieInfos) {
        this.mContext = context;
        this.mMovieInfoList = movieInfos;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FirstHolder(mInflater.inflate(R.layout.template_first_info, parent, false),mOnItemClickListener);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FirstHolder firstHolder = (FirstHolder) holder;
        Glide
                .with(mContext)
                .load(mMovieInfoList.get(position).getImg())
                .into(firstHolder.mImg);
        firstHolder.mName.setText(mMovieInfoList.get(position).getName());
        firstHolder.mScore.setText(mMovieInfoList.get(position).getScore());
        firstHolder.mTxtMax.setText(mMovieInfoList.get(position).getMovieVar());

    }

    @Override
    public int getItemCount() {
        return mMovieInfoList.size();
    }

    class FirstHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.img)
        ImageView mImg;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.txt_max)
        TextView mTxtMax;
        @BindView(R.id.score)
        TextView mScore;

        private OnItemClickListener mOnItemClickListener;
        public FirstHolder(View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
            this.mOnItemClickListener = onItemClickListener;
        }
        @Override
        public void onClick(View v) {
            if(mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(v,getLayoutPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public MovieInfo getMovieInfo(int position){
        return mMovieInfoList.get(position);
    }
}
