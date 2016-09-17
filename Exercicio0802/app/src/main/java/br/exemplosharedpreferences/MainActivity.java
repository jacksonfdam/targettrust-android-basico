package br.exemplosharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	private static final String PREF_NAME = "MainActivityPreferences";
	private int count1;
	private int count2;
	
	private SharedPreferences.OnSharedPreferenceChangeListener callback = new SharedPreferences.OnSharedPreferenceChangeListener(){
		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			Log.i("Script", key+" updated");
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Log.i("Script", "MainActivity");
		
		SharedPreferences sp1 = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		count1 = sp1.getInt("count_1", 0);
		Log.i("Script", "COUNT 1: "+count1);
		sp1.registerOnSharedPreferenceChangeListener(callback);
		
		
		SharedPreferences sp2 = getPreferences(MODE_PRIVATE);
		count2 = sp2.getInt("count_2", 0);
		Log.i("Script", "COUNT 2: "+count2);
	}
	
	
	@Override
	public void onStop() {
		super.onStop();
		
		SharedPreferences sp1 = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		count1 = sp1.getInt("count_1", 0);
		SharedPreferences.Editor editor = sp1.edit();
		editor.putInt("count_1", count1 + 1);
		editor.commit();
		
		SharedPreferences sp2 = getPreferences(MODE_PRIVATE);
		count2 = sp2.getInt("count_2", 0);
		editor = sp2.edit();
		editor.putInt("count_2", count2 + 1);
		editor.commit();
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		SharedPreferences sp1 = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		sp1.unregisterOnSharedPreferenceChangeListener(callback);
		
		/*SharedPreferences.Editor editor = sp1.edit();
		editor.clear();
		editor.commit();
		
		SharedPreferences sp2 = getPreferences(MODE_PRIVATE);
		editor = sp2.edit();
		editor.clear();
		editor.commit();*/
	}
	
	
	public void callSecondActivity(View view){
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		startActivity(intent);
	}
}
