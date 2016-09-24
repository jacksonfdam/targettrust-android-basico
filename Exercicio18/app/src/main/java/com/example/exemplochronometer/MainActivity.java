package com.example.exemplochronometer;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private Chronometer ch;
	private long milliseconds;
	private long millisecondsStop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ch = new Chronometer(this); //(Chronometer) findViewById(R.id.chronometer);
		ch.setText(DateFormat.format("kk:mm:ss", 0));
		ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				long aux = SystemClock.elapsedRealtime() - chronometer.getBase();
				chronometer.setText(DateFormat.format("kk:mm:ss", aux));
			}
		});
		
		milliseconds = 0;
		millisecondsStop = 0;
		
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout);
		ll.addView(ch);
	}
	
	
	public void startChronometer(View view){
		millisecondsStop = millisecondsStop > 0 ? System.currentTimeMillis() - millisecondsStop : 0;
		ch.setBase(SystemClock.elapsedRealtime() - (milliseconds + millisecondsStop));
		ch.start();
		millisecondsStop = 0;
	}
	
	
	public void pauseChronometer(View view){
		millisecondsStop = System.currentTimeMillis();
		milliseconds = SystemClock.elapsedRealtime() - ch.getBase();
		ch.stop();
	}
}
