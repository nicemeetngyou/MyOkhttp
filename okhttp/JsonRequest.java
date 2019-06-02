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
		// ִ�о����������ʲ���

		URL url = null;
		try {

			url = new URL(this.mUrl);
			// ��http����
			mURLConnection = (HttpURLConnection) url.openConnection();
			// �������ӳ�ʱ��ʱ��
			mURLConnection.setConnectTimeout(6000);
			// �����Ƿ�ʹ�û���
			mURLConnection.setUseCaches(false);
			// ���������ڵ�ǰ����������������������Ա��ض���
			mURLConnection.setInstanceFollowRedirects(true);
			// ������Ӧ��ʱʱ��
			mURLConnection.setReadTimeout(3000);
			// ������������Ƿ���Զ�д����
			mURLConnection.setDoInput(true);
			mURLConnection.setDoOutput(true);
			// ��������ʽ
			mURLConnection.setRequestMethod("POST");
			// ������Ϣ����
			mURLConnection.setRequestProperty("Content-Type", "application/json;charset-UTF-8");
			// ���������˵����ñ���Ҫ��connect֮ǰ��ɣ�ʵ������ֻ�ǽ�����һ�����������TCP����
			// ��������
			mURLConnection.connect();
			// ʹ���ֽ�����������
			OutputStream outputStream = mURLConnection.getOutputStream();
			// ���ֽ�����װ�ɻ����ֽ���
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
			// ���ֽ����������д�뻺����
			bufferedOutputStream.write(mData);
			// ˢ�»�����
			bufferedOutputStream.flush();
			outputStream.close();
			bufferedOutputStream.close();

			// �ַ���д������
			if (mURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = mURLConnection.getInputStream();
				mCallbackListener.onSucess(inputStream);

			} else {
				throw new RuntimeException("����ʧ��");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("����ʧ��");
		} finally {
			mURLConnection.disconnect();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
