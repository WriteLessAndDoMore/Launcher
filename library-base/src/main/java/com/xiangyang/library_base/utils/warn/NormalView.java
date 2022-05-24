package com.xiangyang.library_base.utils.warn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;

import com.xiangyang.library_base.utils.LogUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author : sunxd@autoai.com
 * date : 2021/6/25 11
 */
public abstract class NormalView {
//TYPE_XAUTO_ALERT
    private static final int VOLUME_WINDOW_TYPE = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
    private static final int VOLUME_WINDOW_HEIGHT = -1;
    private static final int VOLUME_WINDOW_WIDTH = -1;
    private static final int VOLUME_WINDOW_X = 0;
    private static final int VOLUME_WINDOW_Y = 0;

    protected Context mContext = null;

    private View contentView = null;
    private WindowManager.LayoutParams mVolumeParams = null;
    private final WindowManager mWindowManager;

    protected AtomicBoolean isShowed = new AtomicBoolean(false);

    public NormalView(Context context) {
        LogUtils.d();
        mContext = context;
        mWindowManager = (WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        initParams();
        initView();
    }

    @LayoutRes
    abstract int getLayoutId();

    abstract void viewInflateReady(View v);

    private void initParams() {
        mVolumeParams = new WindowManager.LayoutParams();
        mVolumeParams.type = VOLUME_WINDOW_TYPE;
//        mVolumeParams.width = VOLUME_WINDOW_WIDTH;
//        mVolumeParams.height = VOLUME_WINDOW_HEIGHT;
//        mVolumeParams.x = VOLUME_WINDOW_X;
//        mVolumeParams.y = VOLUME_WINDOW_Y;
        mVolumeParams.gravity = Gravity.START | Gravity.TOP;
        mVolumeParams.format = PixelFormat.RGBA_8888;
        mVolumeParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        contentView = LayoutInflater.from(mContext).inflate(getLayoutId(), null, true);
        viewInflateReady(contentView);
    }

    public void show() {
        LogUtils.d();
        if (!isShowed.get()) {
            isShowed.set(true);
            mWindowManager.addView(contentView, mVolumeParams);
        }
    }

    public void show(String content) {
        LogUtils.d(content);
    }

    public void dismiss() {
        LogUtils.d("dismiss() contentView=>" + contentView);
        if (isShowed.get() && contentView != null) {
            mWindowManager.removeView(contentView);
        }
        isShowed.set(false);
    }

    public boolean isShowing(){
        return isShowed.get();
    }

}
