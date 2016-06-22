package com.example.lampcontrol1.bean;

import java.io.Serializable;


/**
 * @author Tianzheng
 *
 */
public class GetDeviceResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 0:成功 ，1：失败
	 */
	private int response =-1;

	/**
	 * 返回相关提示信息
	 */
	private String message;
	
	/**
	 * 数据结果
	 */
	private  GetDeviceList result ;
	
	
	
	
	public int getResponse() {
		return response;
	}


	public void setResponse(int response) {
		this.response = response;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public GetDeviceList getResult() {
		return result;
	}


	public void setResult(GetDeviceList result) {
		this.result = result;
	}


	
	

	
}
