package com.enjoy.mvpshop;

import com.enjoy.mvpshop.bean.BaseBean;
import com.enjoy.mvpshop.bean.Goods;
import com.enjoy.mvpshop.network.RetrofitClient;
import com.enjoy.mvpshop.network.service.GoodsService;

import org.junit.Test;

import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        GoodsService goodsService = RetrofitClient.getInstance().getService(GoodsService.class);
        goodsService.getGoods().subscribe(new Consumer<BaseBean<List<Goods>>>() {
            @Override
            public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                System.out.println(listBaseBean);
            }
        });

        while (true) {
        }
    }
}