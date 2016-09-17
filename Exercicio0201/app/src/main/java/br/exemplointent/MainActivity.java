package br.exemplointent;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
	}
	
	public void chamaSegundaAtividade(View view){
		
		Intent intent = new Intent(this, SegundaActivity.class);
		startActivity(intent);
		
	}
	
	public void chamaNavegador(View view){
		
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://localhost"));
		startActivity(intent);
		
	}

}
