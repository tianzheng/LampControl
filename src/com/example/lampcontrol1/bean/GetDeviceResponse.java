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
	 * 0:�ɹ� ��1��ʧ��
	 */
	private int response =-1;

	/**
	 * ���������ʾ��Ϣ
	 */
	private String message;
	
	/**
	 * ���ݽ��
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
