package com.xiangyang.library_base.threadpoolexecutor.thread;

/**
 * 线程基类
 * @author sishuai
 * @date 2018/5/29
 */
public abstract class AbstractEasyThread<T>
{
    /**
     * 后台执行函数。
     *
     * @return 执行结果。
     */
    protected abstract T doBackground ();

    /**
     * 执行结果函数。
     *
     * @param t
     *            执行结果。
     */
    protected abstract void onExecuteResult (T t);
}

