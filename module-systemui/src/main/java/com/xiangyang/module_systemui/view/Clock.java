package com.xiangyang.module_systemui.view;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiangyang.module_systemui.R;

import java.text.SimpleDateFormat;

public class Clock extends RelativeLayout {

    private Context mContext;
    private TextView mTimeDisplay;

    public Clock(Context context) {
        this(context, null);
    }

    public Clock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Clock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.systemui_status_bar_clock,
                this, true);
        mTimeDisplay = view.findViewById(R.id.time_display);
    }
    private final CharSequence getSmallTime() {
        Context context = getContext();
        boolean is24 = DateFormat.is24HourFormat(context);

        String format = is24 ? "HH:mm" : "hh:mm";
        SimpleDateFormat sDateFormat = new SimpleDateFormat(is24 ? "HH:mm" : "hh:mm");



        return null;
    }
}
