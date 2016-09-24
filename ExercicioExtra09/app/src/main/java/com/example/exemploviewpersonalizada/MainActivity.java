package com.example.exemploviewpersonalizada;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EmailText et;
	private Quadrado quadrado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*et = new EmailText(this);
		et.setHint("Coloque um email aqui");
		
		quadrado = new Quadrado(this);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		quadrado.setLayoutParams(lp);
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);
		ll.addView(et);
		ll.addView(quadrado);*/
		
	}
	
	public void verificaEmail(View view){
		et = (EmailText) findViewById(R.id.email);
		
		if(et.isEmail()){
			Toast.makeText(this, "Sim", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "Não", Toast.LENGTH_SHORT).show();
		}
	}
}
