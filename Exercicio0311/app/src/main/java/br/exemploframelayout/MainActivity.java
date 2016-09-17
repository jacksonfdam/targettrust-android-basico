package br.exemploframelayout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		FrameLayout fl = new FrameLayout(this);
		
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		fl.setLayoutParams(lp);
		
		TextView tv = new TextView(this);
		tv.setText("Apenas uma teste com FrameLAyout");
		fl.addView(tv);
		
		setContentView(fl);
	}
	
	/*public void enviarFormulario(View view){
		FrameLayout fl = (FrameLayout) findViewById(R.id.fl2);
		fl.setVisibility(View.VISIBLE);
	}*/
}
