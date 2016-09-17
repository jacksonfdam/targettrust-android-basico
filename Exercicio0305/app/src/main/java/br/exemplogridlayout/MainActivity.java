package br.exemplogridlayout;

import br.exemplogridlayout.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		/*ScrollView sv = new ScrollView(this);
		FrameLayout.LayoutParams lpsv = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		sv.setLayoutParams(lpsv);
		
		HorizontalScrollView hsv = new HorizontalScrollView(this);
		hsv.setLayoutParams(lpsv);
		sv.addView(hsv);
		
		
		GridLayout gl = new GridLayout(this);
		hsv.addView(gl);
		
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				GridLayout.Spec linha = GridLayout.spec(i);
				GridLayout.Spec coluna = GridLayout.spec(j);
				GridLayout.LayoutParams lp = new GridLayout.LayoutParams(linha, coluna);
				
				ImageView iv = new ImageView(this);
				iv.setImageResource(R.drawable.ic_launcher);
				
				gl.addView(iv, lp);
			}
		}
		
		setContentView(sv);*/
	}
}
