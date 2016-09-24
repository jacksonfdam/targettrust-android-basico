package br.exemplostylexml;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv = (TextView) findViewById(R.id.textView2);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.setMargins(25, 25, 25, 25);
		tv.setLayoutParams(lp);
		tv.setTextAppearance(this, R.style.textView2_css);
		tv.setBackgroundResource(R.color.amarelo);
		tv.setPadding(20, 20, 20, 20);
	}

}
