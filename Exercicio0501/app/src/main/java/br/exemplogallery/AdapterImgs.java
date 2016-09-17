package br.exemplogallery;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdapterImgs extends BaseAdapter{
	private Context ctx;
	private int[] imgs;
	
	public AdapterImgs(Context ctx, int[] imgs){
		this.ctx = ctx;
		this.imgs = imgs;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imgs.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imgs[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout ll = new LinearLayout(ctx);
		ll.setOrientation(LinearLayout.VERTICAL);
		Gallery.LayoutParams lp = new Gallery.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);

		ImageView iv = new ImageView(ctx);
		lp = new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, 60);
		iv.setLayoutParams(lp);
		iv.setImageResource(imgs[position]);
		ll.addView(iv);
		
		TextView tv = new TextView(ctx);
		tv.setText("Carro "+(position + 1));
		tv.setGravity(Gravity.CENTER);
		ll.addView(tv);
		
		return(ll);
	}
}
