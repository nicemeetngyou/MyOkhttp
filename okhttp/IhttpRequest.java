/**
 * 
 */
package com.xlfx.okhttp;

/**
 * @author xlfx
 *
 */
public interface IhttpRequest {
	void setUrl(String url);
	void setData(byte[] data);
	void setListener(CallbackListener callbackListener);
	
    void excute();
}
