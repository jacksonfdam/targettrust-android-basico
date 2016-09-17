package br.exemplosharedpreferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;
import android.view.Menu;

public class SecondActivity extends Activity {
	private static final String PREF_NAME = "MainActivityPreferences";
	private int count1;
	private int count2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		
		
		Log.i("Script", "SecondActivity");
		
		SharedPreferences sp1 = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		count1 = sp1.getInt("count_1", 0);
		Log.i("Script", "COUNT 1: "+count1);
		
		
		SharedPreferences sp2 = getSharedPreferences("MainActivity", MODE_PRIVATE); //getPreferences(MODE_PRIVATE);
		count2 = sp2.getInt("count_2", 0);
		Log.i("Script", "COUNT 2: "+count2);
	}
}
