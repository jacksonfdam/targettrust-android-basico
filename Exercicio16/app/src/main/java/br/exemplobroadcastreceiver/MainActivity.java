package br.exemplobroadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		IntentFilter it = new IntentFilter();
		it.addAction("BROADCAST_RECEIVER_API");
		it.addCategory(Intent.CATEGORY_DEFAULT);
		
		registerReceiver(new BroadcastReceiver2(), it);
	}
	
	public void chamarBR(View view){
		Button bt = (Button) view;
		
		Intent intent = new Intent(bt.getText().toString());
		
		sendBroadcast(intent);
	}
	
	@Override
	public void onDestroy(){
		unregisterReceiver(new BroadcastReceiver2());
	}
}
