package com.zick.nsm.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/10.
 */
public class MovieInfo implements Serializable{
    private static final String baseUrl="http://maoyan.com";
    private String name;//片名
    private String img;//图像
    private String score;//评分
    private String movieVar;//影视类别 2D/3D
    private String imgDetail;//详情链接
    private String shopping;//立即购买

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMovieVar() {
        return movieVar;
    }

    public void setMovieVar(String movieVar) {
        this.movieVar = movieVar;
    }

    public String getImgDetail() {
        return baseUrl + imgDetail;
    }

    public void setImgDetail(String imgDetail) {
        this.imgDetail = imgDetail;
    }

    public String getShopping() {
        return shopping;
    }

    public void setShopping(String shopping) {
        this.shopping = shopping;
    }

    @Override
    public String toString() {
        return "MovieInfo{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", score='" + score + '\'' +
                ", movie_var='" + movieVar + '\'' +
                ", imgDetail='" + imgDetail + '\'' +
                ", shopping='" + shopping + '\'' +
                '}';
    }
}
