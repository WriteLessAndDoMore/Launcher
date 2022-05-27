package com.xiangyang.module_systemui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.xiangyang.module_systemui.statusbar.StatusBar;

public class StatusBarWindowView extends FrameLayout {
    private StatusBar statusBar;
    public StatusBarWindowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMotionEventSplittingEnabled(false);
    }

    public void setBar(StatusBar statusBar) {
        this.statusBar = statusBar;
    }
}
