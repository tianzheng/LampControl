package com.example.lampcontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lampcontrol.R;
import com.example.lampcontrol.bean.GetDeviceResponse;

public class DeviceAdapter extends BaseAdapter {
	/**
	 * ����һ������
	 */
	private LayoutInflater inflater;
	/**
	 * ����response
	 */
	private GetDeviceResponse response;

	/**
	 * 
	 * ����һ����������ֵ
	 */
	public void setResponse(GetDeviceResponse response) {
		this.response = response;
		notifyDataSetChanged();
		// alt+? ��ݼ�
	}

	public DeviceAdapter(Context context) {
		// ʵ��������
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
		holder.lampName.setText("���֣�"
				+ response.getResult().getDevice().get(position).getName());
		boolean state = response.getResult()
				.getDevice().get(position)
				.isLampState();
		if (state) {
			// ����
			holder.imgLamp.setBackgroundResource(R.drawable.light_on);
			holder.lampState.setText("״̬����");
		} else {
			// �ص�
			holder.imgLamp.setBackgroundResource(R.drawable.light_off);
			holder.lampState.setText("״̬����");
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imgLamp;
		TextView lampName;
		// shift+alt+o ������ݼ�
		TextView lampState;
	}

}
