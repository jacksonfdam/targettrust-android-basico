package br.exemplologcat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private static final String CATEGORIA = "Script";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.v(CATEGORIA, "Verbo - Preto");
		Log.d(CATEGORIA, "Debug - Azul");
		Log.i(CATEGORIA, "Informação - Verde");
		Log.w(CATEGORIA, "Alerta - Laranja");
		
		int i = 2 / 0;
		
		Log.e("Script", "Erro - Vermelho");
	}

}
