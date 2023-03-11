package com.enjoy.mvpshop.ui.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.enjoy.mvpshop.R;
import com.enjoy.mvpshop.bean.Goods;
import com.enjoy.mvpshop.bean.GoodsDetail;
import com.enjoy.mvpshop.ui.base.BaseFragment;
import com.enjoy.mvpshop.ui.detail.GoodsDetailActivity;
import com.enjoy.mvpshop.ui.home.adapter.HomeRecyclerViewAdapter;
import com.enjoy.mvpshop.ui.home.adapter.HomeSpanSizeLookup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, HomeContract.IHomeView, HomeRecyclerViewAdapter.OnItemClickListener {

    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private HomePresenter homePresenter;
    private HomeSpanSizeLookup homeSpanSizeLookup;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String TAG = "HomeFragment";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        swipeRefreshLayout = find(R.id.home_swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = find(R.id.home_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),
                4);
        List<Goods> goods = new ArrayList<>();
        homeSpanSizeLookup = new HomeSpanSizeLookup(goods);
        gridLayoutManager.setSpanSizeLookup(homeSpanSizeLookup);
        recyclerView.setLayoutManager(gridLayoutManager);

        homeRecyclerViewAdapter =
                new HomeRecyclerViewAdapter(recyclerView, getActivity(), goods);
        homeRecyclerViewAdapter.setOnItemClickLisnter(this);
        recyclerView.setAdapter(homeRecyclerViewAdapter);

        homePresenter = new HomePresenter(this);
        homePresenter.getData();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        homePresenter.getData();
    }

    @Override
    public void getGoodsSuccess(List<Goods> goods) {
        homeSpanSizeLookup.setGoods(goods);
        homeRecyclerViewAdapter.setGoods(goods);
    }

    @Override
    public void getGoodsError(Throwable throwable) {
        Log.d(TAG, "getGoodsError: "+throwable.getMessage() );
    }


    @Override
    public void onItemClick(Goods goods) {
        Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
        intent.putExtra(GoodsDetailActivity.GOODS_ID, goods.getGoodsId());
        startActivity(intent);
    }
}
