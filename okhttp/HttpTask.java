/**
 * 
 */
package com.xlfx.okhttp;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;

/**
 * @author xlfx
 * @param <T>
 *
 */
public class HttpTask<T> implements Runnable, Delayed {

	private IhttpRequest mhttpRequest;
	// 重试时间
	private long mDelayTime;

	public long getRetryCount() {
		return mRetryCount;
	}

	public void setRetryCount(long mRetryCount) {
		this.mRetryCount = mRetryCount;
	}

	public void setDelayTime(long mDelayTime) {
		this.mDelayTime = System.currentTimeMillis() + mDelayTime;
	}

	// 重试次数
	private long mRetryCount;

	/**
	 * 构造函数
	 */
	public HttpTask() {
		// TODO Auto-generated constructor stub
	}

	public HttpTask(String url, T requestData, IhttpRequest httpRequest, CallbackListener callbackListener) {
		httpRequest.setUrl(url);
		httpRequest.setListener(callbackListener);
		String content = JSON.toJSONString(requestData);
		try {
			httpRequest.setData(content.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			mhttpRequest.excute();
		} catch (Exception e) {
			//进入重试流程
			ThreadPoolManager.getInstance().addRetryTask(this);
		}
		

	}


	@Override
	public long getDelay(TimeUnit unit) {
		long delay = unit.convert(this.mDelayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		return delay;
		
	}
	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * for test
	 * 
	 * @author xlfx
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
