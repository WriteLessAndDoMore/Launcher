package com.xiangyang.library_base.threadpoolexecutor.thread;

import android.os.Handler;
import android.os.Message;

import com.xiangyang.library_base.threadpoolexecutor.LocalThreadPoolExecutor;
import com.xiangyang.library_base.utils.LogUtils;


/**
 * 线程
 *
 * @author sishuai
 * @date 2018/5/29
 */
public abstract class EasyThread<T> extends AbstractEasyThread<Object> {
    private Handler m_Handler;
    private T m_Result;
    private int mWhat;

    public EasyThread() {
        mWhat = this.hashCode();
        LogUtils.d("hashCode: " + mWhat);
        /**
         * 消息处理。
         */
        m_Handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == mWhat) {
                    onExecuteResult(m_Result);
                }
            }
        };
    }

    public void start() {
        LocalThreadPoolExecutor.getInstance().execute(new Runnable() {
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                // 后台执行。
                m_Result = (T) doBackground();
                // 发送处理结果消息。
                m_Handler.sendEmptyMessage(mWhat);
            }
        });
    }
}

