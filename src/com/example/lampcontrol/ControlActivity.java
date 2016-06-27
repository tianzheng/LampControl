package com.example.lampcontrol;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lampcontrol.bean.Device;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class ControlActivity extends BaseActivity {
	// 设备信息对象
	Device device;


	/**
	 * 灯的显示
	 */
	ImageView lamp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_control);
		getIntentData();
		findView();
	}

	/**
	 * 获取序列化数据
	 */
	private void getIntentData() {
		// 声明，实例化
		Intent intent = getIntent();
		// 获取设备数据
		device = (Device) intent.getExtras().get("device_key");
	}

	private void findView() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 返回或关闭页面
				finish();
			}
		});
		// 灯的显示
		lamp = (ImageView) findViewById(R.id.img_lamp);
		if (device.isLampState()) {
			lamp.setBackgroundResource(R.drawable.light_on);
		} else {
			lamp.setBackgroundResource(R.drawable.light_off);
		}
		// 灯的名称
		TextView name = (TextView) findViewById(R.id.device_name);
		name.setText("名字：" + device.getName());
		// 红光色值
		SeekBar seekBarR = (SeekBar) findViewById(R.id.seekBarR);
		seekBarR.setProgress(device.getLampR());
		// 绿光色值
		SeekBar seekBarG = (SeekBar) findViewById(R.id.seekBarG);
		seekBarG.setProgress(device.getLampG());
		// 蓝光色值
		SeekBar seekBarB = (SeekBar) findViewById(R.id.seekBarB);
		seekBarB.setProgress(device.getLampB());
		// 开灯按钮
		Button open = (Button) findViewById(R.id.open);
		// 关灯按钮
		Button close = (Button) findViewById(R.id.close);

		open.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				device.setLampState(true);
				device.setLampBright(false);
				openOrClose();
			}
		});
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				device.setLampState(false);
				device.setLampBright(false);
				openOrClose();

			}
		});

		seekBarR.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.e("Stop==", "进度=" + seekBar.getProgress());
				// 设置红灯颜色
				device.setLampR(seekBar.getProgress());
				device.setLampBright(true);
				// Http 请求
				requestHttpPost();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.e("Start==", "进度=" + seekBar.getProgress());
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}
		});

		seekBarG.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.e("Stop==", "进度=" + seekBar.getProgress());
				// 设置绿灯颜色
				device.setLampR(seekBar.getProgress());
				device.setLampBright(true);
				// Http 请求
				requestHttpPost();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.e("Start==", "进度=" + seekBar.getProgress());
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}
		});

		seekBarB.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.e("Stop==", "进度=" + seekBar.getProgress());
				// 设置蓝灯颜色
				device.setLampR(seekBar.getProgress());
				device.setLampBright(true);
				// Http 请求
				requestHttpPost();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.e("Start==", "进度=" + seekBar.getProgress());
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}
		});

	}

	/**
	 * Http pos请求
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private void requestHttpPost() {
		// 提示框显示
		dialog.show();
		// 生成Json
		String requsetJson = gson.toJson(device);

		Log.e("requsetJson==", "进度=" + requsetJson);

		// 声明，实例化对象
		RequestParams params = new RequestParams("UTF-8");
		try {
			params.setBodyEntity(new StringEntity(requsetJson, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		params.setContentType("applicatin/json");
		String url = "http://"+ConstantValue.IP+":8080/SmartHomeService/ChangeLightServlet";
		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(ControlActivity.this, "请求失败",
								Toast.LENGTH_LONG).show();
						// 关闭提示框
						dialog.dismiss();
					}

					public void onSuccess(ResponseInfo<String> arg0) {
						Toast.makeText(ControlActivity.this,
								"请求成功" + arg0.result, Toast.LENGTH_LONG).show();
						// 关闭提示框
						dialog.dismiss();
					}
				});

	}

	/**
	 * 开关灯
	 */
	private void openOrClose() {

		// 提示框显示
		dialog.show();
		// 生成Json
		String requsetJson = gson.toJson(device);

		Log.e("requsetJson==", "进度=" + requsetJson);

		// 声明，实例化对象
		RequestParams params = new RequestParams("UTF-8");
		try {
			params.setBodyEntity(new StringEntity(requsetJson, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		params.setContentType("applicatin/json");
		String url = "http://"+ConstantValue.IP+":8080/"
				+ "SmartHomeService/ChangeLightServlet";
		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(ControlActivity.this, "请求失败",
								Toast.LENGTH_LONG).show();
						// 关闭提示框
						dialog.dismiss();
					}

					public void onSuccess(ResponseInfo<String> arg0) {
						Toast.makeText(ControlActivity.this,
								"请求成功" + arg0.result, Toast.LENGTH_LONG).show();
						// 关闭提示框
						dialog.dismiss();
						if (device.isLampState()) {
							lamp.setBackgroundResource(R.drawable.light_on);
						} else {
							lamp.setBackgroundResource(R.drawable.light_off);
						}

					}
				}

		);

	}
}
