package com.liangxiao.gsons;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import com.google.gson.Gson;
public class MainActivity extends Activity {

	LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Task().execute();
	}

	// List<ProviderInfoData>
	public static JSONArray getClassIfy(String result) {
		Log.i("1", "4---进入getClassIfy()");
		// 将大类和小类的json转为类集合
		ProviderInfoData pid;
		List<ProviderInfoData> list = null;
		// getJsonContent("http://itf.zhikaisoft.com/store.aspx?vercode=123456&operation=getproviderlist&localx=31.208994&localy=121.597567&distence=20000");
		Gson gson = new Gson();
		// if (gson != null) {
		// Log.i("1", "进入循环");
		// java.lang.reflect.Type type = new
		// com.google.gson.reflect.TypeToken<List<ProviderInfoData>>() {
		// }.getType();
		pid = gson.fromJson(result, ProviderInfoData.class);
		Log.i("1", "5----" + pid.toString());

		String pp = pid.getStatus();
		Log.i("1", "6-----------" + pp);

		String ppp = pid.getData().getRecordCount();
		Log.i("1", "7-----------" + ppp);

		Provider p = pid.getData().getProviderList().get(0);
		Log.i("1",
				"8" + "Address" + p.getAddress() + "AppLogo" + p.getAppLogo()
						+ "Distance" + p.getDistance() + "ProviderCode"
						+ p.getProviderCode() + "ProviderID"
						+ p.getProviderID() + "StoreName" + p.getStoreName()
						+ "StoreScore" + p.getStoreScore());
		JSONObject jsonObject, jsonObjectData;
		JSONArray jsonArray = null;
		try {
			jsonObject = new JSONObject(result);
			// 将此json数据变成一个对象。
			jsonObjectData = jsonObject.getJSONObject("data");
			jsonArray = jsonObjectData.getJSONArray("ProviderList");

			// return jsonArray;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }
		// Log.i("1", list + "");
		// ---------------------------------------------------
		// try {
		// JSONObject jsonObject, jsonObjectData;
		// jsonObject = new JSONObject(result);
		// // 将此json数据变成一个对象。
		// jsonObjectData = jsonObject.getJSONObject("data");
		// JSONArray jsonArray = jsonObjectData.getJSONArray("ProviderList");
		// //获得list里面的数据
		// List<Provider> lp = new ArrayList<Provider>();
		// for (int i = 0; i < jsonArray.length(); i++) {
		// Log.i("1", "5---进入解析 for循环");
		// Provider p = new Provider();
		// JSONObject jsonOb = (JSONObject) jsonArray.opt(i);
		// p.setAddress(jsonOb.getString("Address"));
		// p.setAppLogo(jsonOb.getString("AppLogo"));
		// p.setDistance(jsonOb.getString("Distance"));
		// p.setProviderCode(jsonOb.getString("ProviderCode"));
		// p.setProviderID(jsonOb.getString("ProviderID"));
		// p.setStoreName(jsonOb.getString("StoreName"));
		// p.setStoreScore(jsonOb.getString("StoreScore"));
		// lp.add(p);
		// }
		// for (int i = 0; i < lp.size(); i++) {
		// Provider p = (Provider)lp.get(i);
		// Log.i("1",
		// "Address"+p.getAddress()+"AppLogo"+p.getAppLogo()+"Distance"+p.getDistance()+"ProviderCode"+p.getProviderCode()+"ProviderID"+p.getProviderID()+"StoreName"+p.getStoreName()+"StoreScore"+p.getStoreScore());
		// }
		// return list;
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return null;
		return jsonArray;
	}

	public final class Task extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			String jsonStr = getJsonContent("http://itf.zhikaisoft.com/store.aspx?vercode=123456&operation=getproviderlist&localx=31.208994&localy=121.597567&distence=20000");
			Log.i("1", "2" + jsonStr.toString());
			return jsonStr;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Log.i("1", "3" + result.toString());
			getClassIfy(result);
		}

	}

	public static String getJsonContent(String urlStr) {
		try {// 获取HttpURLConnection连接对象
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(3000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				Log.i("1", "1");
				return ConvertStream2Json(httpConn.getInputStream());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private static String ConvertStream2Json(InputStream inputStream) {
		String jsonStr = "";
		// ByteArrayOutputStream相当于内存输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		try {
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
			}
			// 将内存流转换为字符串
			jsonStr = new String(out.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}
}