package com.example.lampcontrol.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Device implements Serializable{


	private static final long serialVersionUID = 1L;
	/**
	 * �豸ID
	 */
	private	int id;
	/**
	 * �豸���ƣ�os01
	 */
	private	String name;
	
	
	
	
	

	/**
	 * ����ֵ0-100
	 */
	private  int lampR;
	/**
	 * ����ֵ0-100
	 */
	private  int lampG;
	/**
	 * ����ֵ0-100
	 */
	private  int lampB;
	
	
	/**
	 * ture �������ȣ� false ����
	 */
	private boolean  lampBright;
	
	public boolean isLampBright() {
		return lampBright;
	}
	public void setLampBright(boolean lampBright) {
		this.lampBright = lampBright;
	}
	/**
	 * true���ƣ�false�ص�
	 */
	private  boolean  lampState;

	private boolean online;
	
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLampR() {
		return lampR;
	}
	public void setLampR(int lampR) {
		this.lampR = lampR;
	}
	public int getLampG() {
		return lampG;
	}
	public void setLampG(int lampG) {
		this.lampG = lampG;
	}
	public int getLampB() {
		return lampB;
	}
	public void setLampB(int lampB) {
		this.lampB = lampB;
	}
	public boolean isLampState() {
		return lampState;
	}
	public void setLampState(boolean lampState) {
		this.lampState = lampState;
	}

	


}
