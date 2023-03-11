package com.enjoy.mvpshop.network.service;

import com.enjoy.mvpshop.bean.BaseBean;
import com.enjoy.mvpshop.bean.Goods;
import com.enjoy.mvpshop.bean.GoodsDetail;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoodsService {
//
//    @GET("edu-lance/edu-lance.github.io/master/goods_list")
//    Flowable<BaseBean<List<Goods>>> getGoods();
//
//    @GET("edu-lance/edu-lance.github.io/master/goods_detail")
//    Flowable<BaseBean<GoodsDetail>> getGoodDetail(@Query("goodsId") int goodsId);

    @GET("get_data.json")
    Flowable<BaseBean<List<Goods>>> getGoods();

    @GET("getGoodDetailInfo.json")
    Flowable<BaseBean<GoodsDetail>> getGoodDetail(@Query("goodsId") int goodsId);
}
