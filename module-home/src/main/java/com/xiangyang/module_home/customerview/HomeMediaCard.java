package com.xiangyang.module_home.customerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.xiangyang.module_home.databinding.HomeMediaCardBinding;

public class HomeMediaCard extends RelativeLayout {

    private HomeMediaCardBinding mBinding;

    public HomeMediaCard(Context context) {
        this(context,null);
    }

    public HomeMediaCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeMediaCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = HomeMediaCardBinding.inflate(inflater, this, true);
    }
}
