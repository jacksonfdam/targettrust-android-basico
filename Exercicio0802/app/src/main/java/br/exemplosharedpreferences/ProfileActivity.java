package br.exemplosharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class ProfileActivity extends Activity {
	private static final String PREF_NAME = "LoginActivityPreferences";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_activity);
	}
	
	
	public void signOut(View view){
		SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear().commit();
		
		finish();
	}
}
