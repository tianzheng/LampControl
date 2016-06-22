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
	  //声明对象
	  GetDeviceResponse response;
	  //声明ListView控件
	  ListView lampList;
	  //声明device适配器
	  DeviceAdapter adapter; 
	  //文件改名字  shift+alt+r
	  //提示键  alt+?
	  //自动导包 shift+alt+o
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
* 初始化View
*/
private void findView() {
lampList = (ListView) findViewById(R.id.lamp_list);
adapter = new DeviceAdapter(MainActivity.this);
lampList.setAdapter(adapter);
lampList.setOnItemClickListener(new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//声明一个对象
		Device device=null;
		device=response.getResult().getDevice().get(position);
		//声明Intent
        Intent intent;
        //实例化对象
        intent=new Intent();
        //设置跳转类
        intent.setClass(MainActivity.this,
        		ControlActivity.class);
        //序列化传值
        intent.putExtra("device_key", device);
        //启动跳转
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
 * 设置数据
 */
private void loadData(){
	//声明对象，实例化
	  response=new GetDeviceResponse();
	  response.setMessage("请求网络成功");
	  response.setResponse(0);
	  //声明result对象，实例化
	  GetDeviceList result=new GetDeviceList();
	  //声明ArrayList对象，实例化
	  ArrayList<Device> deviceList=new ArrayList<Device>();
	//声明Device 1对象，实例化
	  Device device1=new Device();
	  device1.setId(1);
	  device1.setName("s01");
	  device1.setLampR(150);
	  device1.setLampG(100);
	  device1.setLampB(50);
	  device1.setLampState(true);
		//声明Device 2对象，实例化
	  Device device2=new Device();
	  device2.setId(2);
	  device2.setName("s02");
	  device2.setLampR(255);
	  device2.setLampG(200);
	  device2.setLampB(10);
	  device2.setLampState(false);
	  deviceList.add(device1);
	  deviceList.add(device2);
	  //赋值
	  result.setDevice(deviceList);
	  //设置数组大小
	  result.setTotal(deviceList.size());
	  
      response.setResult(result);

      //设置适配器数据
      adapter.setResponse(response);

}
	
/**
 * 请求网络
 */
private void requestHttp(){
	long time=System.currentTimeMillis(); 
	String url="http://"+ConstantValue.IP+":8080/SmartHomeService/ShowLightsServlet?time="+time;
httpUtils.send(HttpMethod.GET,url,
new RequestCallBack<String>() {
public void onFailure(HttpException arg0, String arg1) {
	Log.e("", "百度=1="+arg1);
	dialog.dismiss();
	Toast.makeText(MainActivity.this,
			"请求失败", Toast.LENGTH_SHORT).show();
}
		public void onSuccess(ResponseInfo<String> arg0) {
			Log.e("", "百度=2="+arg0.result);
		
response=gson.fromJson(arg0.result, GetDeviceResponse.class);
			 //设置适配器数据
Log.e("", "百度=response="
			 +response.getResult().getDevice().size());
adapter.setResponse(response);
		  	dialog.dismiss();
		  	Toast.makeText(MainActivity.this,
					"请求成功", Toast.LENGTH_SHORT).show();
		}
	} );
	
}


}
