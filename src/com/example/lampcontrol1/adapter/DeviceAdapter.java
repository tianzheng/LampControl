package com.example.lampcontrol1.adapter;

import com.example.lampcontrol1.R;
import com.example.lampcontrol1.bean.GetDeviceResponse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DeviceAdapter extends BaseAdapter {
	/**
	 * 声明一个对象
	 */
	private LayoutInflater inflater;
	/**
	 * 声明response
	 */
	private GetDeviceResponse response;

	/**
	 * 
	 * 创建一个方法，传值
	 */
	public void setResponse(GetDeviceResponse response) {
		this.response = response;
		notifyDataSetChanged();
		// alt+? 快捷键
	}

	public DeviceAdapter(Context context) {
		// 实例化对象
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return response == null ? 0 : response.getResult().getDevice().size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.device_item, null);
			holder.imgLamp = (ImageView) convertView
					.findViewById(R.id.img_lamp);
			holder.lampName = (TextView) convertView
					.findViewById(R.id.lamp_name);
			holder.lampState = (TextView) convertView
					.findViewById(R.id.lamp_state);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.lampName.setText("名字："
				+ response.getResult().getDevice().get(position).getName());
		boolean state = response.getResult()
				.getDevice().get(position)
				.isLampState();
		if (state) {
			// 开灯
			holder.imgLamp.setBackgroundResource(R.drawable.light_on);
			holder.lampState.setText("状态：开");
		} else {
			// 关灯
			holder.imgLamp.setBackgroundResource(R.drawable.light_off);
			holder.lampState.setText("状态：关");
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imgLamp;
		TextView lampName;
		// shift+alt+o 导包快捷键
		TextView lampState;
	}

}
