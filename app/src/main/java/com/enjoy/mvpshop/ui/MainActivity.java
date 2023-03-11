package com.enjoy.mvpshop.ui;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.enjoy.mvpshop.R;
import com.enjoy.mvpshop.ui.base.BaseActivity;
import com.enjoy.mvpshop.ui.cart.CartFragment;
import com.enjoy.mvpshop.ui.home.HomeFragment;
import com.enjoy.mvpshop.ui.mine.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment[] fragments;
    private int lastFragmentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MVPShop); //一定要是super.onCreate前调用
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        BottomNavigationView buttomNavigationView =
                find(R.id.main_buttom_navigation);
        buttomNavigationView.setOnNavigationItemSelectedListener(this);

        fragments = new Fragment[]{
                new HomeFragment(),
                new CartFragment(),
                new MineFragment()};

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame, fragments[0])
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.buttom_home:
                switchFragment(0);
                break;
            case R.id.buttom_cart:
                switchFragment(1);
                break;
            case R.id.buttom_mine:
                switchFragment(2);
                break;
        }
        return false;
    }

    private void switchFragment(int to) {
        if (lastFragmentIndex == to) {
            return;
        }
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        if (!fragments[to].isAdded()) {
            fragmentTransaction.add(R.id.main_frame, fragments[to]);
        } else {
            fragmentTransaction.show(fragments[to]);
        }
        fragmentTransaction.hide(fragments[lastFragmentIndex])
                .commitAllowingStateLoss();
        lastFragmentIndex = to;
    }
}