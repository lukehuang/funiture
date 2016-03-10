package com.app.mvc.common;import java.util.concurrent.BlockingQueue;import java.util.concurrent.ExecutorService;import java.util.concurrent.Future;import java.util.concurrent.LinkedBlockingQueue;import java.util.concurrent.RejectedExecutionException;import java.util.concurrent.RejectedExecutionHandler;import java.util.concurrent.ThreadPoolExecutor;import java.util.concurrent.TimeUnit;/** * Created by jimin on 15/12/1. */public class ThreadPool {    private static final int corePoolSize = 40;// 核心池大小    private static final int maximumPoolSize = 100;// 最大线程数    private static final long keepAliveTime = 2;// 空闲等待时间    private static final TimeUnit timeUnit = TimeUnit.MINUTES;// 时间单位    private static final int queueSize = 200;// 队列大小    private static final BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>(queueSize);// 队列    private static final RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();// 线程调用运行该任务的 execute 本身.此策略提供简单的反馈控制机制,能够减缓新任务的提交速度.    private static ExecutorService pool;// 线程池    /****** 初始化线程池 ******/    static {        pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, blockingQueue, rejectedExecutionHandler);    }    /**     * 在未来某个时间执行给定的命令     */    public static void execute(Runnable runnable) throws RejectedExecutionException {        pool.execute(runnable);    }    /**     * 提交一个 Runnable 任务用于执行,并返回一个表示该任务的 Future     */    public static Future<?> submit(Runnable runnable) throws RejectedExecutionException {        return pool.submit(runnable);    }}