package com.example.lampcontrol.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author
 * 返回数据结果
 *
 */
public class GetDeviceList implements Serializable{


	private static final long serialVersionUID = 1L;
	/**
	 * 后台设备总数
	 */
	private int total;
	/**
	 * 设备集合
	 */
	private ArrayList<Device> device;



	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ArrayList<Device> getDevice() {
		return device;
	}

	public void setDevice(ArrayList<Device> device) {
		this.device = device;
	}



}
