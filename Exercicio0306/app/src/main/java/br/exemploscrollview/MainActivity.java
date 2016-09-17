package br.exemploscrollview;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		/*HorizontalScrollView sv = new HorizontalScrollView(this);
		FrameLayout.LayoutParams lpsv = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		sv.setLayoutParams(lpsv);
		
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams lpll = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lpll);
		sv.addView(ll);
		
		for(int i = 0; i < 50; i++){
			TextView tv = new TextView(this);
			tv.setText("Texto Exemplo "+(i+1)+" - ");
			ll.addView(tv);
		}
		
		setContentView(sv);*/
	}
}