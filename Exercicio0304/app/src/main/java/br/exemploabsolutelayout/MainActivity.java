package br.exemploabsolutelayout;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		/*AbsoluteLayout al = new AbsoluteLayout(this);
		AbsoluteLayout.LayoutParams lp = new AbsoluteLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 0, 0);
		al.setLayoutParams(lp);
		
		TextView tv = new TextView(this);
		tv.setText("Usuário: ");
		lp = new AbsoluteLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, (int)getResources().getDimension(R.dimen.dezesseis_dp), (int)getResources().getDimension(R.dimen.vinte_dp));
		tv.setLayoutParams(lp);
		al.addView(tv);
		
		EditText et = new EditText(this);
		lp = new AbsoluteLayout.LayoutParams((int)getResources().getDimension(R.dimen.cem_dp), LayoutParams.WRAP_CONTENT, (int)getResources().getDimension(R.dimen.cem_dp), (int)getResources().getDimension(R.dimen.dez_dp));
		et.setLayoutParams(lp);
		al.addView(et);
		
		setContentView(al);*/
	}

}
