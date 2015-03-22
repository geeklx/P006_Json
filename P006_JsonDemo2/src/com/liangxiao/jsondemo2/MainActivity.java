package com.liangxiao.jsondemo2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv;
	private TextView tv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		tv2 = (TextView) findViewById(R.id.tv2);
		reloadData();

	}

	public void reloadData() {
		new MyAsyncTask().execute("");
	}

	public class MyAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null) {
				try {
					JSONArray jsonArray = new JSONArray(result);
					JSONObject jsonObject = jsonArray.getJSONObject(0);
					JSONArray pollutants = jsonObject
							.getJSONArray("pollutants");
					JSONObject fistpollutants = pollutants.getJSONObject(0);
					tv.setText(String.format("%s %s:%f",
							jsonObject.getString("cityName"),
							jsonObject.getString("localName"),
							fistpollutants.getDouble("value")));

					JSONArray jsonArray2 = new JSONArray(result);
					JSONObject jsonObject2 = jsonArray2.getJSONObject(1);
					JSONArray pollutants2 = jsonObject2
							.getJSONArray("pollutants");
					JSONObject fistpollutants2 = pollutants2.getJSONObject(0);
					tv2.setText(String.format("%s %s:%f",
							jsonObject2.getString("cityName"),
							jsonObject2.getString("localName"),
							fistpollutants2.getDouble("value")));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected String doInBackground(String... params) {

			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(new URL(
								"http://aqicn.org/publishingdata/json")
								.openStream(), "utf-8"));
				String line = null;
				StringBuffer content = new StringBuffer();
				while ((line = reader.readLine()) != null) {
					content.append(line);
				}
				reader.close();
				return content.toString();
			} catch (Exception e) {
				e.getStackTrace();
			}
			return null;
		}

	}

}
