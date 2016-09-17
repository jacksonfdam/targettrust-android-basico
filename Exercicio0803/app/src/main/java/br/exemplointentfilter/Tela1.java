package br.exemplointentfilter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class Tela1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela1);
	}
	
	public void voltar(View view){
		finish();
	}
}
