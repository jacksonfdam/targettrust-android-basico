package br.exemplosharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private static final String PREF_NAME = "LoginActivityPreferences";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		// VERIFY SHAREDPREFERENCES
			SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
			String login = sp.getString("login", "");
			String password = sp.getString("password", "");
			
			if(login.equals("admin") && password.equals("123")){
				Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
				startActivity(intent);
			}
	}
	
	
	public void signIn(View view){
		EditText etLogin = (EditText) findViewById(R.id.login);
		EditText etPassword = (EditText) findViewById(R.id.password);
		CheckBox saveLogin = (CheckBox) findViewById(R.id.saveLogin);
		
		String login = etLogin.getText().toString();
		String password = etPassword.getText().toString();
		
		if(login.equals("admin") && password.equals("123")){
			
			// VERIFY CHECKBOX FOR SHAREDPREFERENCES
				if(saveLogin.isChecked()){
					SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					
					editor.putString("login", login);
					editor.putString("password", password);
					editor.commit();
				}
			
			Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
			startActivity(intent);
		}
		else{
			Toast.makeText(LoginActivity.this, "Dados errados!", Toast.LENGTH_SHORT).show();
		}
	}
}
