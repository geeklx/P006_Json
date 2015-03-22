package com.liangxiao.volley;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Context mContext;
	private ImageView iv;
	private NetworkImageView ni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		getJsonVolley();
		iv = (ImageView) findViewById(R.id.iv);
		ni = (NetworkImageView) findViewById(R.id.iv2);
		String requestUrl = "http://www.baidu.com/img/bd_logo1.png";
		LoadImageVolley(requestUrl, iv);
		NetworkImageViewVolley(requestUrl, ni);
	}

	// 获取json字符串
	public void getJsonVolley() {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		String JSONDateUrl = "http://apis.juhe.cn/ip/ip2addr?ip=www.juhe.cn&key=aad9614cdf1b35cf7ce6400c152cb723";
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.POST, JSONDateUrl, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject arg0) {
						System.out.println("Response=" + arg0);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						System.out.println("error=" + arg0);
					}
				});
		requestQueue.add(jsonObjectRequest);
	}

	// 获取图片 http://www.baidu.com/img/bd_logo1.png
	public void LoadImageVolley(String requestUrl, ImageView iv) {

		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(
				20);
		ImageCache imageCache = new ImageCache() {

			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}

			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};
		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		ImageListener listener = imageLoader.getImageListener(iv,
				R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get(requestUrl, listener);
	}

	// 获取图片2
	public void NetworkImageViewVolley(String requestUrl, NetworkImageView ni) {
		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(
				20);
		ImageCache imageCache = new ImageCache() {

			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}

			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};
		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		ni.setTag("url");
		ni.setImageUrl(requestUrl, imageLoader);
	}
}
