package br.exemplogallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements ViewFactory {
	final int[] carros = {R.drawable.ferrari_laferrari, R.drawable.bugatti_veyron, R.drawable.pagani_zonda,
						R.drawable.porsche_911, R.drawable.mclaren, R.drawable.maserati_gran_turismo,
						R.drawable.lamborghini_veneno};
	
	@Override
	public void onCreate(Bundle saveInstaceState){
		super.onCreate(saveInstaceState);
		setContentView(R.layout.activity_main);
		
		final ImageSwitcher is = new ImageSwitcher(this); //(ImageSwitcher) findViewById(R.id.imageSwitcher);
		ImageSwitcher.LayoutParams lp = new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		is.setLayoutParams(lp);
		is.setFactory(this);
		is.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
		is.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);
		ll.addView(is, 1);
		
		
		Gallery ga =  (Gallery) findViewById(R.id.gallery);
		ga.setAdapter(new AdapterImgs(this, carros));
		ga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				is.setImageResource(carros[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
			
		});
	}

	@Override
	public View makeView() {
		ImageView iv = new ImageView(this);
		iv.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return iv;
	}
}
