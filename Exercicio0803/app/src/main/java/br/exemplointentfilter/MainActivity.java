package br.exemplointentfilter;

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
	
	public void dispararAcaoTela(View view){
		Intent intent = new Intent("ACAO_TELA");
		startActivity(intent);
	}
	
	public void dispararAcaoTelaCategoriaTela(View view){
		Intent intent = new Intent("ACAO_TELA");
		intent.addCategory("CATEGORIA_TELA");
		startActivity(intent);
	}
	
	public void abrirNavegador(View view){
		
		Uri uri = Uri.parse("http://localhost");
		
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		
		startActivity(intent);
	}
}
