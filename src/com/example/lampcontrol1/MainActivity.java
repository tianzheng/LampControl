package com.example.lampcontrol1;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lampcontrol1.adapter.DeviceAdapter;
import com.example.lampcontrol1.bean.Device;
import com.example.lampcontrol1.bean.GetDeviceList;
import com.example.lampcontrol1.bean.GetDeviceResponse;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MainActivity extends BaseActivity {
	  //��������
	  GetDeviceResponse response;
	  //����ListView�ؼ�
	  ListView lampList;
	  //����device������
	  DeviceAdapter adapter; 
	  //�ļ�������  shift+alt+r
	  //��ʾ��  alt+?
	  //�Զ����� shift+alt+o
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
	//	loadData();
	}
	protected void onResume() {
		requestHttp();
		super.onResume();
	}
	
/**
* ��ʼ��View
*/
private void findView() {
lampList = (ListView) findViewById(R.id.lamp_list);
adapter = new DeviceAdapter(MainActivity.this);
lampList.setAdapter(adapter);
lampList.setOnItemClickListener(new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//����һ������
		Device device=null;
		device=response.getResult().getDevice().get(position);
		//����Intent
        Intent intent;
        //ʵ��������
        intent=new Intent();
        //������ת��
        intent.setClass(MainActivity.this,
        		ControlActivity.class);
        //���л���ֵ
        intent.putExtra("device_key", device);
        //������ת
        startActivity(intent);
	}});




Button refresh=(Button)findViewById(R.id.refresh);
refresh.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		dialog.show();
		requestHttp();
	}
});

}
	



  /**
 * ��������
 */
private void loadData(){
	//��������ʵ����
	  response=new GetDeviceResponse();
	  response.setMessage("��������ɹ�");
	  response.setResponse(0);
	  //����result����ʵ����
	  GetDeviceList result=new GetDeviceList();
	  //����ArrayList����ʵ����
	  ArrayList<Device> deviceList=new ArrayList<Device>();
	//����Device 1����ʵ����
	  Device device1=new Device();
	  device1.setId(1);
	  device1.setName("s01");
	  device1.setLampR(150);
	  device1.setLampG(100);
	  device1.setLampB(50);
	  device1.setLampState(true);
		//����Device 2����ʵ����
	  Device device2=new Device();
	  device2.setId(2);
	  device2.setName("s02");
	  device2.setLampR(255);
	  device2.setLampG(200);
	  device2.setLampB(10);
	  device2.setLampState(false);
	  deviceList.add(device1);
	  deviceList.add(device2);
	  //��ֵ
	  result.setDevice(deviceList);
	  //���������С
	  result.setTotal(deviceList.size());
	  
      response.setResult(result);

      //��������������
      adapter.setResponse(response);

}
	
/**
 * ��������
 */
private void requestHttp(){
	long time=System.currentTimeMillis(); 
	String url="http://"+ConstantValue.IP+":8080/SmartHomeService/ShowLightsServlet?time="+time;
httpUtils.send(HttpMethod.GET,url,
new RequestCallBack<String>() {
public void onFailure(HttpException arg0, String arg1) {
	Log.e("", "�ٶ�=1="+arg1);
	dialog.dismiss();
	Toast.makeText(MainActivity.this,
			"����ʧ��", Toast.LENGTH_SHORT).show();
}
		public void onSuccess(ResponseInfo<String> arg0) {
			Log.e("", "�ٶ�=2="+arg0.result);
		
response=gson.fromJson(arg0.result, GetDeviceResponse.class);
			 //��������������
Log.e("", "�ٶ�=response="
			 +response.getResult().getDevice().size());
adapter.setResponse(response);
		  	dialog.dismiss();
		  	Toast.makeText(MainActivity.this,
					"����ɹ�", Toast.LENGTH_SHORT).show();
		}
	} );
	
}


}
