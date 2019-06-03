/**
 * 
 */
package com.xlfx.okhttp;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;

import android.os.Handler;
import android.os.Looper;

/**
 * @author Administrator
 * @param <T>
 *
 */
public class JsonRequestCallbackListener<T> implements CallbackListener {

	private Class<T> mResponseClass;
	private IJsonDataListener mJsonDataListener;
	// 切换到Android UI主线程
	private Handler mHandler = new Handler(Looper.getMainLooper());

	/**
	 * 
	 */
	public JsonRequestCallbackListener(Class<T> responseClass, IJsonDataListener jsonDataListener) {
		this.mResponseClass = responseClass;
		this.mJsonDataListener = jsonDataListener;
	}

	public JsonRequestCallbackListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onSucess(InputStream inputStream) {
		String response = getContent(inputStream);
		final T clazz = JSON.parseObject(response, mResponseClass);
        mHandler.post(new  Runnable() {
			
			@Override
			public void run() {
				mJsonDataListener.onSuccess(clazz);
				
				
			}
		});
        
	}

	private String getContent(InputStream inputStream) {
		String content = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			try {
				while (null != (line = bufferedReader.readLine())) {

					stringBuffer.append(line + '\n');
				}
			} catch (Exception e) {
				System.out.println("Error = " + e.toString());
			} finally {
				try {
					inputStream.close();
				} catch (Exception e2) {
					System.out.println("Error = " + e2.toString());
				}
			}
			return stringBuffer.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}

	@Override
	public void onFail() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
