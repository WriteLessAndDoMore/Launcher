package com.xiangyang.module_home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiangyang.module_home.adapter.HomePagerAdapter;
import com.xiangyang.module_home.databinding.ActivityMainBinding;
import com.xiangyang.module_home.util.AppViewModelFactory;
import com.xiangyang.module_home.view.FirstPageFragment;
import com.xiangyang.module_home.view.SecondPageFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/home/MainActivity")
public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{

    private HomePagerAdapter mPagerAdapter;
    private ActivityMainBinding mBinding;
    private List<ImageView> listDoc;
    private List<Fragment> listFragment;
    private LinearLayout llDoc;
    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();


    }

    private void initView() {
        listFragment = new ArrayList<>();
        listFragment.add(new FirstPageFragment());
        listFragment.add(new SecondPageFragment());
        mPagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), listFragment);
        mVp = mBinding.homeViewPager;
        mVp.setAdapter(mPagerAdapter);
//        mVp.addOnAdapterChangeListener(this);
        addDoc();
        mVp.addOnPageChangeListener(this);
    }


    //小圆点
    private void addDoc() {
        listDoc = new ArrayList<ImageView>();

        llDoc = mBinding.llDoc;
        for (int i = 0; i < listFragment.size()+1; i++) {

            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 10;
            iv.setLayoutParams(params);
            if (i == 0) {
                iv.setImageResource(R.drawable.doc_check);
            } else {
                iv.setImageResource(R.drawable.doc_uncheck);
            }
            // 添加小圆点
            listDoc.add(iv);
            // 添加小圆点视图
            llDoc.addView(iv);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int index = position % listDoc.size();
        for (int i = 0; i < listDoc.size(); i++) {
            listDoc.get(i).setImageResource(R.drawable.doc_uncheck);
        }
            listDoc.get(index).setImageResource(R.drawable.doc_check);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
