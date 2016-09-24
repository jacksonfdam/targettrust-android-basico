package br.exemploservicegw;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void startService(View view){
		Intent it = new Intent("SERVICO_TEST");
		it.putExtra("aas", "sdvsd");
		startService(it);
	}
	
	public void stopService(View view){
		Intent it = new Intent("SERVICO_TEST");
		stopService(it);
	}
}
