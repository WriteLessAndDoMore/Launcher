package com.xiangyang.library_base.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * 最优线程池
 * @author sishuai
 * @date 2018/5/30
 */
public class LocalThreadPoolExecutor
{
    private static ThreadPoolExecutor mThreadPoolExecutor;
    private static LocalThreadPoolExecutor instance;
    /**
     * cpu核心数
     **/
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 核心线程数 除非allowCoreThreadTimeOut被设置为true，否则它闲着也不会死
     **/
    private static final int corePoolSize = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    /**
     * 最大线程数，活动线程数量超过它，后续任务就会排队
     * 如果是CPU密集型应用,则线程池大小设置为N+1
     * 如果是IO密集型应用,则线程池大小设置为2N+1
     **/
    private static final int maximumPoolSize = CPU_COUNT * 2 + 1;
    /**
     * 超时时长，作用于非核心线程（allowCoreThreadTimeOut被设置为true时也会同时作用于核心线程），闲置超时便被回收
     **/
    private final static int keepAliveTime = 30;
    /**
     * 缓存队列的容量
     * **/
    private final static int capacity = 30;

    /**
     * 构造方法
     * 始化线程池
     **/
    public LocalThreadPoolExecutor()
    {
        //任务过多后,存储任务的一个阻塞队列/缓冲任务队列，线程池的execute方法会将Runnable对象存储起来
        BlockingQueue workQueue = new ArrayBlockingQueue(capacity);
        //线程工厂接口，只有一个new Thread(Runnable r)方法，可为线程池创建新线程
        ThreadFactory threadFactory = new DefaultThreadFactory();
        //创建线程池
        mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, threadFactory);
    }

    /**
     * @return 单例化
     **/
    public static LocalThreadPoolExecutor getInstance()
    {
        if (instance == null)
        {
            synchronized (LocalThreadPoolExecutor.class)
            {
                if (instance == null)
                {
                    instance = new LocalThreadPoolExecutor();
                }
            }
        }
        return instance;
    }

    /**
     * 通过线程池执行任务
     *
     * @param runnable 需要执行的任务
     **/
    public void execute(Runnable runnable)
    {
        mThreadPoolExecutor.execute(runnable);
    }
}
