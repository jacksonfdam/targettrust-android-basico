package com.example.exemplobateriadispositivo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends Activity {
	//private BroadcastBateria bb = new BroadcastBateria();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//registerReceiver(bb, new IntentFilter(Intent.ACTION_BATTERY_LOW));
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		
		//unregisterReceiver(bb);
	}
}
