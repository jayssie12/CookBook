package com.cb.nb.zk;

import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import static android.content.ContentValues.TAG;

public class CListAdapter extends BaseAdapter {
	
	private CListActivity activity;
	
	private List<Cook> dataList;
	
	public CListAdapter(CListActivity activity, List<Cook> dataList) {
		this.activity = activity;
		this.dataList = dataList;
		//��ʼ��Android-Universal-Image-Loader���
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(activity));
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(activity).inflate(R.layout.clist_item, null);
		final Cook c = dataList.get(position);
		((TextView)convertView.findViewById(R.id.clist_item_title)).setText(c.name);
		((TextView)convertView.findViewById(R.id.clist_item_detail)).setText(c.description);
		//����ͼƬ��ʾ��ʽ(���ǿ�������Բ�ǡ������һЩ������)
		DisplayImageOptions options = new DisplayImageOptions.Builder()  
	        .showImageOnLoading(R.drawable.loading)  	//��������ʾ�����ڼ����е�ͼƬ
	        .showImageOnFail(R.drawable.loading)  		//Ϊ�˷��㣬����ʧ��Ҳ��ʾ�����е�ͼƬ
	        .cacheInMemory(true)  					//���ڴ��л���ͼƬ
	        .cacheOnDisk(true)  					
	        .bitmapConfig(Bitmap.Config.RGB_565)
	        .imageScaleType(ImageScaleType.EXACTLY)		//����ͼƬ����εı��뷽ʽ��ʾ
	        .build(); 
		//�첽����ͼƬ������Ⱦ��ָ���Ŀؼ���
		ImageLoader.getInstance().displayImage(MUtils.PREFIX_IMG + c.img, (ImageView)convertView.findViewById(R.id.clist_item_icon), options);
		//Item��������ʳ���������
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, DetailActivity.class);
				intent.putExtra("id", c.id);
				intent.putExtra("name", c.name);
				activity.startActivity(intent);
			}
		});
		return convertView;
	}

	//ΪAdapter�����µ�ʳ�����ݣ�����ҳ����ʱ�����ڸ��·�ҳ��Ϣ
	public void add(List<Cook> newData) {
		dataList.addAll(newData);
	}
}
