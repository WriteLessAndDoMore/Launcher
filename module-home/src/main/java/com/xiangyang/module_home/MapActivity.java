package com.xiangyang.module_home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiangyang.module_home.databinding.ActivityMapBinding;

@Route(path = "/home/MapActivity")
public class MapActivity extends FragmentActivity {

    private ActivityMapBinding mBinding;
    private ImageView mIvButtonLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_map);
        initView();
        initListener();

    }

    private void initListener() {
        mIvButtonLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/home/MainActivity").navigation();
            }
        });
    }

    private void initView() {
        mIvButtonLaunch = mBinding.ivButtonLaunch;
    }
}