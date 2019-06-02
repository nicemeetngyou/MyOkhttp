/**
 * 
 */
package com.xlfx.okhttp;

import com.alibaba.fastjson.JSON;

/**
 * @author xlfx
 * @param <T>
 *
 */
public class HttpTask<T> implements Runnable {

	private IhttpRequest mhttpRequest;

	/**
	 * ¹¹Ôìº¯Êý
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
		mhttpRequest.excute();

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
