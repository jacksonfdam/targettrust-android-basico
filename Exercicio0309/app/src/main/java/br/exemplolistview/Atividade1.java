package br.exemplolistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Atividade1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atividade1);
	}
	
	public void voltar(View view){
		finish();
	}
}
