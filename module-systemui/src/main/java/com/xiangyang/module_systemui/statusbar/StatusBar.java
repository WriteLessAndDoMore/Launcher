package com.xiangyang.module_systemui.statusbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xiangyang.module_systemui.R;
import com.xiangyang.module_systemui.view.StatusBarWindowView;

public class StatusBar {
    private static final String TAG = "StatusBar";
    private Context mContext;
    private final WindowManager windowManager;
    private StatusBarWindowView mStatusBarWindowView;
    private int mNaturalBarHeight = -1;
    private RelativeLayout mStatusBar;
    private ImageView mBt;

    @SuppressLint("ResourceType")
    public StatusBar(Context context) {
        Log.d(TAG, "StatusBar: ");
        this.mContext = context;
        initSkin();
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Resources res = mContext.getResources();
        res.getDimensionPixelSize(98);
        makeStatusBarView();


    }

    private void makeStatusBarView() {
        mStatusBarWindowView = (StatusBarWindowView) View.inflate(mContext,
                R.layout.systemui_status_bar_window, null);
        addStatusBarView(mStatusBarWindowView);
        mStatusBarWindowView.setBar(this);
        mStatusBar = mStatusBarWindowView.findViewById(R.id.status_bar);
        mBt = mStatusBar.findViewById(R.id.iv_icon_bt);
        addStatusBarView();

    }

    private void addStatusBarView() {
        mStatusBar.removeAllViews();
        mStatusBar.addView(mStatusBarWindowView);


    }

    private void addStatusBarView(View windowView) {
        WindowManager.LayoutParams layoutParams = getStatusBarLayoutParams();
        if (windowManager != null) {
            windowManager.addView(windowView, layoutParams);
            windowManager.updateViewLayout(windowView, layoutParams);
        }

    }

    private WindowManager.LayoutParams mLp;

    private WindowManager.LayoutParams getStatusBarLayoutParams() {
        mLp = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                mNaturalBarHeight,
                WindowManager.LayoutParams.TYPE_STATUS_BAR,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_TOUCHABLE_WHEN_WAKING
                        | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,
                PixelFormat.TRANSLUCENT);
        mLp.token = new Binder();
        mLp.gravity = Gravity.TOP;
        mLp.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
        mLp.setTitle("ZhongQi-StatusBar");
        mLp.packageName = mContext.getPackageName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mLp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
        }
        return mLp;
    }

    private void initSkin() {

    }
}
