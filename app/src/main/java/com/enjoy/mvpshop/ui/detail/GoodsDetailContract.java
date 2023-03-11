package com.enjoy.mvpshop.ui.detail;

import com.enjoy.mvpshop.bean.BaseBean;
import com.enjoy.mvpshop.bean.Goods;
import com.enjoy.mvpshop.bean.GoodsDetail;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface GoodsDetailContract {

    interface IGoodsDetailPresenter {
        void getGoodsDetail(int goodsId);
    }

    interface IGoodsDetailModel {
        Flowable<BaseBean<GoodsDetail>> getGoodsDetail(int goodsId);
    }

    interface IGoodsDetailView {

        void getGoodsDetailSuccess(GoodsDetail goodsDetail);

        void getGoodsDetailError(Throwable throwable);
    }
}
