/**
 * 
 */
package com.xlfx.okhttp;

import java.io.InputStream;

/**
 * @author xlfx
 *
 */
public interface CallbackListener {
	void onSucess(InputStream inputStream);

	void onFail();
}
