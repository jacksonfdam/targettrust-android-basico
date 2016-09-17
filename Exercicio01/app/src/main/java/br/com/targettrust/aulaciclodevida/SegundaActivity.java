package br.com.targettrust.aulaciclodevida;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SegundaActivity extends Activity {

	private static final String TAG = "SegundaActivity";

	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_segunda);
	
		tv = (TextView) findViewById(R.id.textView2);
		
		int idade = getIntent().getIntExtra("idade", -1);
		String nome = getIntent().getStringExtra("nome");
		
		tv.setText(nome + " - " + idade);
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}
	
    @Override
    protected void onStart() {
    	super.onStart();

    	Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
    	super.onStop();
    	Log.d(TAG, "onStop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Log.d(TAG, "onDestroy");
    }


	public void voltar (View v) {
		finish();
	}
	
}
