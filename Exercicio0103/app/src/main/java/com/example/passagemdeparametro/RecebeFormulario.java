package com.example.passagemdeparametro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class RecebeFormulario extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recebe_formulario);
		
		
		Intent intent = getIntent();
		
		if(intent != null){
			Bundle params = intent.getExtras();
			
			if(params != null){
				String nome = params.getString("nome");
				String email = params.getString("email");
				
				TextView nomeView = (TextView) findViewById(R.id.nomeTexto);
				TextView emailView = (TextView) findViewById(R.id.emailTexto);
				
				nomeView.setText(nome);
				emailView.setText(email);
			}
		}
	}

}
