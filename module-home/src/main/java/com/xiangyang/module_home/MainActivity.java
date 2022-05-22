package com.xiangyang.module_home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiangyang.module_home.adapter.HomePagerAdapter;
import com.xiangyang.module_home.databinding.ActivityMainBinding;

@Route(path = "/home/MainActivity")
public class MainActivity extends AppCompatActivity implements ViewPager.OnAdapterChangeListener {

    private HomePagerAdapter mPagerAdapter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mPagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
         mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
         mBinding.homeViewPager.setAdapter(mPagerAdapter);
        mBinding.homeViewPager.addOnAdapterChangeListener(this);





    }

    @Override
    public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {

    }
}
