package com.enjoy.mvpshop.ui.home;

import com.enjoy.mvpshop.bean.BaseBean;
import com.enjoy.mvpshop.bean.Goods;
import com.enjoy.mvpshop.network.RetrofitClient;
import com.enjoy.mvpshop.network.service.GoodsService;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class HomeModel implements HomeContract.IHomeModel {
    //从网络获取
    @Override
    public Flowable<BaseBean<List<Goods>>> getData() {
        //....
        return RetrofitClient.getInstance().getService(GoodsService.class)
                .getGoods();
    }
}
