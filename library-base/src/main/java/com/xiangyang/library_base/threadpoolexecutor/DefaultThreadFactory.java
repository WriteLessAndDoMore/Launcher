package com.xiangyang.library_base.threadpoolexecutor;


import androidx.annotation.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sishuai
 * @date 2018/5/30
 */
public class DefaultThreadFactory implements ThreadFactory
{
    /**
     * 原子类 线程池编号
     **/
    private final AtomicInteger poolNumber = new AtomicInteger(1);
    /**
     * 线程数目
     **/
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    /**
     * 线程组
     **/
    private ThreadGroup group;
    /**
     * 为每个创建的线程添加的前缀
     **/
    private final String namePrefix;

    public DefaultThreadFactory()
    {
        SecurityManager s = System.getSecurityManager();
        //取得线程组
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(@NonNull Runnable runnable)
    {
        //真正创建线程的地方，设置了线程的线程组及线程名
        Thread thread = new Thread(group, runnable
                , namePrefix + threadNumber.getAndIncrement());
        if (thread.isDaemon())
        {
            thread.setDaemon(false);
        }
        //默认是正常优先级
        if (thread.getPriority() != Thread.NORM_PRIORITY)
        {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
