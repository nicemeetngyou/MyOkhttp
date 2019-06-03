/**
 * 
 */
package com.xlfx.okhttp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.os.Handler;
import android.util.Log;

/**
 * @author Administrator
 *
 */
public class ThreadPoolManager {

	// 单例-singleton
	private static ThreadPoolManager mThreadPoolManager = new ThreadPoolManager();

	// 任务队列
	// 排序+先进先出+生产着消费者模式
	private LinkedBlockingQueue<Runnable> mBlockingQueue = new LinkedBlockingQueue<>();

	// 延迟队列 for重试
	private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();
	// 线程池
	private ThreadPoolExecutor mThreadPoolExecutor;

	// 单例
	public static ThreadPoolManager getInstance() {

		return mThreadPoolManager;
	}

	/**
	 * 单例 构造函数 {@mThreadPoolExecutor} 初始化
	 */
	private ThreadPoolManager() {
		// TODO Auto-generated constructor stub
		mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4),
				new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						// TODO Auto-generated method stub
						addTask(r);
					}
				});

		// 线程池管理类需要管理调度线程
		mThreadPoolExecutor.execute(coreThread);
		// 线程池管理类需要管理重试调度线程
		mThreadPoolExecutor.execute(retryThread);
	}

	// 添加网络请求任务到队列中
	public void addTask(Runnable r) {
		mBlockingQueue.add(r);

	}

	// 创建调度线程，不断地从队列中查询请求，并交给线程池处理
	public Runnable coreThread = new Runnable() {
		Runnable run = null;

		@Override
		public void run() {
			while (true) {
				try {
					run = mBlockingQueue.take();
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				mThreadPoolExecutor.execute(run);
			}

		}
	};

	public void addRetryTask(HttpTask httpTask) {
		if (null != httpTask) {
			httpTask.setDelayTime(3000);
			mDelayQueue.offer(httpTask);
		}

	}

	// 创建重试任务调度线程
	public Runnable retryThread = new Runnable() {
		HttpTask httpTask = null;

		@Override
		public void run() {
			while (true) {
				try {
					httpTask = mDelayQueue.take();
					if (3 > httpTask.getRetryCount()) {
						mThreadPoolExecutor.execute(httpTask);
						httpTask.setRetryCount(httpTask.getRetryCount() + 1);
						Log.d("==进入重试机制== ", "" + httpTask.getRetryCount() + System.currentTimeMillis());
					}else {
						Log.e("==进入重试机制== ", "重试已经超过3次！放弃重试！");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
