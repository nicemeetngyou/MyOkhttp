/**
 * 
 */
package com.xlfx.okhttp;

/**
 * @author Administrator
 *
 */
public class ResponseClass {

	/**
	 * 
	 */
	public ResponseClass() {
		// TODO Auto-generated constructor stub
	}
    private int resultCode;
    private String reason;
	
	
	@Override
	public String toString() {
		return "ResponseClass [resultCode=" + resultCode + ", reason=" + reason + ", getResultCode()=" + getResultCode()
				+ ", getReason()=" + getReason() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}


	public int getResultCode() {
		return resultCode;
	}


	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
