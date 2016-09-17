package br.exemplosqlite;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	public void getActivity(View view){
		Button bt = (Button) view;
		Intent intent;
		
		if(bt.getText().toString().equalsIgnoreCase("Novo usuário")){
			intent = new Intent(this, NewUserActivity.class);
		}
		else{
			intent = new Intent(this, ListUsersActivity.class);
		}
		
		startActivity(intent);
	}

}
