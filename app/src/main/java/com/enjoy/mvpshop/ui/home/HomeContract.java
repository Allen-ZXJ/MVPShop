package com.enjoy.mvpshop.ui.home;

import com.enjoy.mvpshop.bean.BaseBean;
import com.enjoy.mvpshop.bean.Goods;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface HomeContract {
    interface IHomePresenter {
        void getData();
    }

    interface IHomeModel {
        Flowable<BaseBean<List<Goods>>> getData();
    }

    interface IHomeView {

        void getGoodsSuccess(List<Goods> goods);

        void getGoodsError(Throwable throwable);
    }
}
