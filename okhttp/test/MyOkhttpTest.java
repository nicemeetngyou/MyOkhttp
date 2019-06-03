/**
 * 
 */
package com.xlfx.okhttp.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xlfx.okhttp.IJsonDataListener;
import com.xlfx.okhttp.ResponseClass;
import com.xlfx.okhttp.utils.HttpUtil;

import android.util.Log;

/**
 * @author Administrator
 *
 */
public class MyOkhttpTest {

	private String mURL;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mURL = "";
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
		sendHttpRequest();

	}

	private void sendHttpRequest() {
		// 已经在形参中完成泛型转换
		HttpUtil.sendJsonRequest(mURL, null, ResponseClass.class, new IJsonDataListener<ResponseClass>() {

			@Override
			public void onSuccess(ResponseClass clazz) {
				// TODO Auto-generated method stub
				Log.d("==>", clazz.toString());
			}

			@Override
			public void onFailure() {
				// TODO Auto-generated method stub

			}

		});
		// 常规泛型操作
		// HttpUtil.sendJsonRequest(mURL, null, ResponseClass.class, new
		// IJsonDataListener<Object>() {
		//
		// @Override
		// public void onFailure() {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onSuccess(Object clazz) {
		// ResponseClass responseClass = (ResponseClass) clazz;
		// Log.d("==>", responseClass.toString());
		// // TODO Auto-generated method stub
		//
		// }
		// });
	}

}
