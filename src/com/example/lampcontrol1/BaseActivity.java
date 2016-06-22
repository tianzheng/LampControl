package com.example.lampcontrol1;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

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
    	httpUtils=new HttpUtils();
    	gson=new Gson();
    	dialog=new ProgressDialog(BaseActivity.this);
    	dialog.setTitle("数据请求");
    	dialog.setMessage("努力加载中......");
    }
}
