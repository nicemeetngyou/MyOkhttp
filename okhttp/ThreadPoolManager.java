/**
 * 
 */
package com.xlfx.okhttp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.os.Handler;

/**
 * @author Administrator
 *
 */
public class ThreadPoolManager {
 
	// ����-singleton
	private static ThreadPoolManager mThreadPoolManager = new ThreadPoolManager();

	// �������
	// ����+�Ƚ��ȳ�+������������ģʽ
	private LinkedBlockingQueue<Runnable> mBlockingQueue = new LinkedBlockingQueue<>();

	// �̳߳�
	private ThreadPoolExecutor mThreadPoolExecutor;

	// ����
	public static ThreadPoolManager getInstance() {

		return mThreadPoolManager;
	}

	/**
	 * ���� ���캯�� {@mThreadPoolExecutor} ��ʼ��
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

		// �̳߳ع�������Ҫ��������߳�
		mThreadPoolExecutor.execute(coreThread);
	}

	// ��������������񵽶�����
	public void addTask(Runnable r) {
		mBlockingQueue.add(r);

	}

	// ���������̣߳����ϵشӶ����в�ѯ���󣬲������̳߳ش���
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
