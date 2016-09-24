package com.example.exemploviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private int[] carros = {R.drawable.ferrari_laferrari, R.drawable.bugatti_veyron, R.drawable.pagani_zonda,
							R.drawable.porsche_911, R.drawable.mclaren, R.drawable.maserati_gran_turismo,
							R.drawable.lamborghini_veneno};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ViewPager vp = new ViewPager(this); //(ViewPager) findViewById(R.id.viewPager);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		vp.setLayoutParams(lp);
		
		vp.setAdapter(new AdapterImg(this, carros));
		
		vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				Log.i("Script", "onPageSelected()");
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.i("Script", "onPageScrolled()");
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				Log.i("Script", "onPageScrollStateChanged()");
			}
		});
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);
		ll.addView(vp);
	}

}
