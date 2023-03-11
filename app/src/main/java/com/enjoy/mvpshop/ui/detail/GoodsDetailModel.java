package com.enjoy.mvpshop.ui.detail;

import com.enjoy.mvpshop.bean.BaseBean;
import com.enjoy.mvpshop.bean.GoodsDetail;
import com.enjoy.mvpshop.network.RetrofitClient;
import com.enjoy.mvpshop.network.service.GoodsService;

import io.reactivex.rxjava3.core.Flowable;

public class GoodsDetailModel implements GoodsDetailContract.IGoodsDetailModel {
    @Override
    public Flowable<BaseBean<GoodsDetail>> getGoodsDetail(int goodsId) {
        return RetrofitClient.getInstance().getService(GoodsService.class)
                .getGoodDetail(goodsId);
    }
}
