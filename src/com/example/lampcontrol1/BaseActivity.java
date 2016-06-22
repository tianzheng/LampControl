package com.example.lampcontrol1;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

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
    	httpUtils=new HttpUtils();
    	gson=new Gson();
    	dialog=new ProgressDialog(BaseActivity.this);
    	dialog.setTitle("��������");
    	dialog.setMessage("Ŭ��������......");
    }
}
