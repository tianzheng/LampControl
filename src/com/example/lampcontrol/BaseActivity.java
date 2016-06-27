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
	 * 声明Http请求对象
	 */
	HttpUtils httpUtils;

	/**
	 * 声明Gson对象
	 */
	Gson gson;

	/**
	 * Dilog对象
	 */
	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 透明状态栏
		getWindow()
				.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		// 透明底部导航栏
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		httpUtils = new HttpUtils();
		gson = new Gson();
		dialog = new ProgressDialog(BaseActivity.this);
		dialog.setTitle("数据请求");
		dialog.setMessage("努力加载中......");
	}
}
