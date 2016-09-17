package com.example.passagemdeparametro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	public void enviarFormulario(View view){
		
		EditText nome = (EditText) findViewById(R.id.nomeCampo);
		EditText email = (EditText) findViewById(R.id.emailCampo);
		
		Bundle params = new Bundle();
		params.putString("nome", nome.getText().toString());
		params.putString("email", email.getText().toString());
		
		Intent intent = new Intent(this, RecebeFormulario.class);
		intent.putExtras(params);
		
		startActivity(intent);
	}

}
