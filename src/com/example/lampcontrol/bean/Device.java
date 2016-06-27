package com.example.lampcontrol.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Device implements Serializable{


	private static final long serialVersionUID = 1L;
	/**
	 * 设备ID
	 */
	private	int id;
	/**
	 * 设备名称：os01
	 */
	private	String name;
	
	
	
	
	

	/**
	 * 红数值0-100
	 */
	private  int lampR;
	/**
	 * 绿数值0-100
	 */
	private  int lampG;
	/**
	 * 蓝数值0-100
	 */
	private  int lampB;
	
	
	/**
	 * ture 调节亮度， false 忽略
	 */
	private boolean  lampBright;
	
	public boolean isLampBright() {
		return lampBright;
	}
	public void setLampBright(boolean lampBright) {
		this.lampBright = lampBright;
	}
	/**
	 * true开灯，false关灯
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
