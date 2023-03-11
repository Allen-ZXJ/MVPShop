package com.enjoy.mvpshop.ui.detail;

import com.enjoy.mvpshop.bean.BaseBean;
import com.enjoy.mvpshop.bean.GoodsDetail;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GoodsDetailPresenter implements GoodsDetailContract.IGoodsDetailPresenter {

    private final GoodsDetailContract.IGoodsDetailView goodsDetailView;
    GoodsDetailContract.IGoodsDetailModel goodsDetailModel;


    public GoodsDetailPresenter(GoodsDetailContract.IGoodsDetailView goodsDetailView) {
        goodsDetailModel = new GoodsDetailModel();
        this.goodsDetailView = goodsDetailView;
    }

    @Override
    public void getGoodsDetail(int goodsId) {
            goodsDetailModel.getGoodsDetail(goodsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<GoodsDetail>>() {
                               @Override
                               public void accept(BaseBean<GoodsDetail> goodsDetailBaseBean) throws Throwable {
                                   goodsDetailView.getGoodsDetailSuccess(goodsDetailBaseBean.getData());
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                goodsDetailView.getGoodsDetailError(throwable);
                            }
                        });
    }
}
