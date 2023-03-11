package com.enjoy.mvpshop.ui.home;

import android.util.Log;

import com.enjoy.mvpshop.bean.BaseBean;
import com.enjoy.mvpshop.bean.Goods;

import java.util.List;
import java.util.concurrent.BlockingDeque;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements HomeContract.IHomePresenter {

    private HomeContract.IHomeView homeView;
    private HomeContract.IHomeModel homeModel;

    public HomePresenter(HomeContract.IHomeView homeView) {
        this.homeView = homeView;
        homeModel = new HomeModel();
    }

    @Override
    public void getData() {
        homeModel.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<Goods>>>() {
                    @Override
                    public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                        homeView.getGoodsSuccess(listBaseBean.getData());
                        Log.d("Present", "accept: " + listBaseBean.getData().get(0).toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        homeView.getGoodsError(throwable);
                    }
                });
    }
}
