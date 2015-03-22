package com.liangxiao.jsondemo1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		new MyAsyncTask().execute("");
	}

	public class MyAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			tv.setText(result);
		}

		@Override
		protected void onPreExecute() {
			
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				InputStream in = new URL("http://jikexueyuan.com").openStream();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in, "utf-8"));
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
