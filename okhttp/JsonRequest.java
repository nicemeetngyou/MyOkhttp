/**
 * 
 */
package com.xlfx.okhttp;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Administrator
 *
 */
public class JsonRequest implements IhttpRequest {
	private String mUrl;
	private byte[] mData;
	private CallbackListener mCallbackListener;
	private HttpURLConnection mURLConnection;

	/**
	 * 
	 */
	public JsonRequest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */

	@Override
	public void setUrl(String url) {
		this.mUrl = url;

	}

	@Override
	public void setData(byte[] data) {
		this.mData = data;

	}

	@Override
	public void setListener(CallbackListener callbackListener) {
		this.mCallbackListener = callbackListener;

	}

	public String getUrl() {
		return mUrl;
	}

	public byte[] getData() {
		return mData;
	}

	public CallbackListener getCallbackListener() {
		return mCallbackListener;
	}

	@Override
	public void excute() {
		// 执行具体的网络访问操作

		URL url = null;
		try {

			url = new URL(this.mUrl);
			// 打开http连接
			mURLConnection = (HttpURLConnection) url.openConnection();
			// 设置连接超时的时间
			mURLConnection.setConnectTimeout(6000);
			// 设置是否使用缓存
			mURLConnection.setUseCaches(false);
			// 仅仅作用于当前函数，设置这个链接书否可以被重定向
			mURLConnection.setInstanceFollowRedirects(true);
			// 设置响应超时时间
			mURLConnection.setReadTimeout(3000);
			// 设置这个链接是否可以读写数据
			mURLConnection.setDoInput(true);
			mURLConnection.setDoOutput(true);
			// 设置请求方式
			mURLConnection.setRequestMethod("POST");
			// 设置消息类型
			mURLConnection.setRequestProperty("Content-Type", "application/json;charset-UTF-8");
			// 从上述至此的配置必须要在connect之前完成，实际上它只是建立了一个与服务器的TCP连接
			// 建立连接
			mURLConnection.connect();
			// 使用字节流发送数据
			OutputStream outputStream = mURLConnection.getOutputStream();
			// 将字节流包装成缓冲字节流
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
			// 把字节数组的数据写入缓冲区
			bufferedOutputStream.write(mData);
			// 刷新缓冲区
			bufferedOutputStream.flush();
			outputStream.close();
			bufferedOutputStream.close();

			// 字符流写入数据
			if (mURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = mURLConnection.getInputStream();
				mCallbackListener.onSucess(inputStream);

			} else {
				throw new RuntimeException("请求失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("请求失败");
		} finally {
			mURLConnection.disconnect();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
