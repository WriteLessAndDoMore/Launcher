package com.xiangyang.module_home.customerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.xiangyang.module_home.databinding.HomeMediaCardBinding;
import com.xiangyang.module_home.databinding.HomeSoundCardBinding;

public class HomeSoundCard extends RelativeLayout {

    private HomeSoundCardBinding mBinding;

    public HomeSoundCard(Context context) {
        this(context,null);
    }

    public HomeSoundCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeSoundCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = HomeSoundCardBinding.inflate(inflater,this, true);
    }
}
