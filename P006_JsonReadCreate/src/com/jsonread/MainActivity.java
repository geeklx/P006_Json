package com.jsonread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.JsonToken;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView json_text;
	private TextView json_text2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		jsonread();
		jsonmade();
	}

	/**
	 * 读取json数据部分
	 */
	private void jsonread() {
		json_text = (TextView) findViewById(R.id.json_text);
		try {
			InputStreamReader isr = new InputStreamReader(getAssets().open(
					"test.json"), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String Line;
			StringBuilder builder = new StringBuilder();
			while ((Line = br.readLine()) != null) {
				builder.append(Line);
			}
			isr.close();
			br.close();
			JSONObject root = new JSONObject(builder.toString());
			System.out.println("cat = " + root.getString("cat"));
			String jsonString = "cat=" + root.getString("cat") + "\n";
			JSONArray array = root.getJSONArray("languages");
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);

				String a = "----------------------";
				int b = object.getInt("id");
				String c = object.getString("name");
				String d = object.getString("ide");
				jsonString += a + "\n" + "id=" + b + "" + "\n" + "name=" + c
						+ "\n" + "ide=" + d + "\n";
				System.out.println(a);
				System.out.println("id=" + b);
				System.out.println("name=" + c);
				System.out.println("ide=" + d);
			}
			json_text.setText(jsonString);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 读取json数据部分
	 */
	private void jsonmade() {
		json_text2 = (TextView) findViewById(R.id.json_text2);

		try {
			JSONObject root = new JSONObject();
			root.put("cat", "it");
			// {"id":1,"name":"Java","ide":"Eclipse"},
			JSONObject lan1 = new JSONObject();
			lan1.put("id", 1);
			lan1.put("ide", "Eclipse");
			lan1.put("name", "Java");
			// {"id":2,"name":"Swift","ide":"Xcode"},
			JSONObject lan2 = new JSONObject();
			lan2.put("id", 2);
			lan2.put("ide", "Swift");
			lan2.put("name", "Swift");
			// {"id":3,"name":"C#","ide":"Visual Studio"}
			JSONObject lan3 = new JSONObject();
			lan3.put("id", 3);
			lan3.put("ide", "Visual Studio");
			lan3.put("name", "C#");
			// 创建数组
			JSONArray array = new JSONArray();
			array.put(lan1);
			array.put(lan1);
			array.put(lan1);
			root.put("languages", array);
			System.out.println(root.toString());
			json_text2.setText(root.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
