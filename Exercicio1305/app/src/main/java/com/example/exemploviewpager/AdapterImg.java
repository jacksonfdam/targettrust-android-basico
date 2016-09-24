package com.example.exemploviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdapterImg extends PagerAdapter {
	private Context context;
	private int[] imgs;
	
	public AdapterImg(Context context, int[] imgs){
		this.context = context;
		this.imgs = imgs;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imgs.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		// TODO Auto-generated method stub
		Log.i("Script", "view == obj: "+((view == obj) ? "1" : "0"));
		Log.i("Script", "view == ((TextView) obj).getParent(): "+((view == ((TextView) obj).getParent()) ? "1" : "0"));
		return view == ((TextView) obj).getParent();
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position){
		
		LinearLayout ll = new LinearLayout(context);
		ll.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		container.addView(ll);
		
		ImageView iv = new ImageView(context);
		iv.setImageResource(imgs[position]);
		ll.addView(iv);
		
		TextView tv = new TextView(context);
		tv.setText("Carro "+(position +1));
		ll.addView(tv);
		
		Log.i("Script", "Build: Carro: "+(position + 1));
		
		return(tv);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object view){
		//Log.i("Script", "Destroy: Carro: "+(position + 1));
		container.removeView((View)((TextView)view).getParent());
	}
	
}
