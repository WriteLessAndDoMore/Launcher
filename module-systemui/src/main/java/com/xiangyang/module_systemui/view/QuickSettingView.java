package com.xiangyang.module_systemui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


public class QuickSettingView extends RelativeLayout {
    private static final String TAG = QuickSettingView.class.getSimpleName();


    public QuickSettingView(Context context) {
        this(context, null);
    }

//    private float x1, y1;


    public QuickSettingView(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        LogUtils.i(TAG, "onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        LogUtils.i(TAG, "onDetachedFromWindow");
    }
}
