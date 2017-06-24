package com.cb.nb.zk;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends Activity implements OnClickListener, Runnable {

	private TextView titleView, contentView, foodView, nameView, keywordsView,messageView;
	private ImageView imgView;
	private int id;
	private Cook cook = new Cook();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		titleView = (TextView) findViewById(R.id.detail_title);
		contentView = (TextView) findViewById(R.id.detail_text);
		foodView = (TextView) findViewById(R.id.detail_food);
		keywordsView = (TextView) findViewById(R.id.detail_kerywords);
		messageView = (TextView) findViewById(R.id.detail_message);
		nameView = (TextView) findViewById(R.id.detail_name);
		imgView = (ImageView) findViewById(R.id.detail_img);
		findViewById(R.id.detail_return).setOnClickListener(this);
		initData();
	}
	
	private void initData() {
		id = getIntent().getIntExtra("id", 1);
		titleView.setText(getIntent().getStringExtra("name"));
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		cook = MUtils.show(id);
		handler.sendEmptyMessage(0);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.detail_return:
			DetailActivity.this.finish();
			break;

		default:
			break;
		}
	}
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//�첽��ʾͼƬ
//			ImageLoader.getInstance().displayImage(MUtils.PREFIX_IMG + cook.img, imgView);
			nameView.setText(cook.name);
			foodView.setText(cook.food);
			keywordsView.setText(cook.keywords);
			//������Html�ķ�ʽչʾ
			contentView.setText(cook.description);
			messageView.setText(Html.fromHtml(cook.message));
		}
	};
}
