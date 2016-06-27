package com.example.lampcontrol;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;

public class BaseActivity extends Activity {
	/**
	 * ����Http�������
	 */
	HttpUtils httpUtils;

	/**
	 * ����Gson����
	 */
	Gson gson;

	/**
	 * Dilog����
	 */
	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ͸��״̬��
		getWindow()
				.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		// ͸���ײ�������
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		httpUtils = new HttpUtils();
		gson = new Gson();
		dialog = new ProgressDialog(BaseActivity.this);
		dialog.setTitle("��������");
		dialog.setMessage("Ŭ��������......");
	}
}
