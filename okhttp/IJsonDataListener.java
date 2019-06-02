/**
 * 
 */
package com.xlfx.okhttp;

/**
 * @author Administrator
 * @param <T>
 *
 */
public interface IJsonDataListener<T> {

 void onSuccess(T clazz);
 void onFailure();


}
