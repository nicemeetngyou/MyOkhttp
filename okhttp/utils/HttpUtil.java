/**
 * 
 */
package com.xlfx.okhttp.utils;

import com.xlfx.okhttp.CallbackListener;
import com.xlfx.okhttp.HttpTask;
import com.xlfx.okhttp.IJsonDataListener;
import com.xlfx.okhttp.IhttpRequest;
import com.xlfx.okhttp.JsonRequest;
import com.xlfx.okhttp.JsonRequestCallbackListener;
import com.xlfx.okhttp.ThreadPoolManager;

/**
 * @author Administrator
 *
 */
public class HttpUtil {

	/**
	 * 
	 */
	public HttpUtil() {
		// TODO Auto-generated constructor stub
	}

	public static <M, T> void sendJsonRequest(String url, T requestData, Class<M> responseClass,
			IJsonDataListener jsonDataListener) {
		IhttpRequest httpRequest = new JsonRequest();
		CallbackListener callbackListener = new JsonRequestCallbackListener<>(responseClass, jsonDataListener);
		HttpTask<T> httpTask = new HttpTask<T>(url, requestData, httpRequest, callbackListener);
		ThreadPoolManager.getInstance().addTask(httpTask);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
