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
	// �豸��Ϣ����
	Device device;


	/**
	 * �Ƶ���ʾ
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
	 * ��ȡ���л�����
	 */
	private void getIntentData() {
		// ������ʵ����
		Intent intent = getIntent();
		// ��ȡ�豸����
		device = (Device) intent.getExtras().get("device_key");
	}

	private void findView() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ���ػ�ر�ҳ��
				finish();
			}
		});
		// �Ƶ���ʾ
		lamp = (ImageView) findViewById(R.id.img_lamp);
		if (device.isLampState()) {
			lamp.setBackgroundResource(R.drawable.light_on);
		} else {
			lamp.setBackgroundResource(R.drawable.light_off);
		}
		// �Ƶ�����
		TextView name = (TextView) findViewById(R.id.device_name);
		name.setText("���֣�" + device.getName());
		// ���ɫֵ
		SeekBar seekBarR = (SeekBar) findViewById(R.id.seekBarR);
		seekBarR.setProgress(device.getLampR());
		// �̹�ɫֵ
		SeekBar seekBarG = (SeekBar) findViewById(R.id.seekBarG);
		seekBarG.setProgress(device.getLampG());
		// ����ɫֵ
		SeekBar seekBarB = (SeekBar) findViewById(R.id.seekBarB);
		seekBarB.setProgress(device.getLampB());
		// ���ư�ť
		Button open = (Button) findViewById(R.id.open);
		// �صư�ť
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
				Log.e("Stop==", "����=" + seekBar.getProgress());
				// ���ú����ɫ
				device.setLampR(seekBar.getProgress());
				device.setLampBright(true);
				// Http ����
				requestHttpPost();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.e("Start==", "����=" + seekBar.getProgress());
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}
		});

		seekBarG.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.e("Stop==", "����=" + seekBar.getProgress());
				// �����̵���ɫ
				device.setLampR(seekBar.getProgress());
				device.setLampBright(true);
				// Http ����
				requestHttpPost();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.e("Start==", "����=" + seekBar.getProgress());
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}
		});

		seekBarB.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.e("Stop==", "����=" + seekBar.getProgress());
				// ����������ɫ
				device.setLampR(seekBar.getProgress());
				device.setLampBright(true);
				// Http ����
				requestHttpPost();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.e("Start==", "����=" + seekBar.getProgress());
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}
		});

	}

	/**
	 * Http pos����
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private void requestHttpPost() {
		// ��ʾ����ʾ
		dialog.show();
		// ����Json
		String requsetJson = gson.toJson(device);

		Log.e("requsetJson==", "����=" + requsetJson);

		// ������ʵ��������
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
						Toast.makeText(ControlActivity.this, "����ʧ��",
								Toast.LENGTH_LONG).show();
						// �ر���ʾ��
						dialog.dismiss();
					}

					public void onSuccess(ResponseInfo<String> arg0) {
						Toast.makeText(ControlActivity.this,
								"����ɹ�" + arg0.result, Toast.LENGTH_LONG).show();
						// �ر���ʾ��
						dialog.dismiss();
					}
				});

	}

	/**
	 * ���ص�
	 */
	private void openOrClose() {

		// ��ʾ����ʾ
		dialog.show();
		// ����Json
		String requsetJson = gson.toJson(device);

		Log.e("requsetJson==", "����=" + requsetJson);

		// ������ʵ��������
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
						Toast.makeText(ControlActivity.this, "����ʧ��",
								Toast.LENGTH_LONG).show();
						// �ر���ʾ��
						dialog.dismiss();
					}

					public void onSuccess(ResponseInfo<String> arg0) {
						Toast.makeText(ControlActivity.this,
								"����ɹ�" + arg0.result, Toast.LENGTH_LONG).show();
						// �ر���ʾ��
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
