package br.exemplowebview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.JavascriptInterface;

public class MainActivity extends Activity {
	private String url = "http://localhost/index.php?";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		WebView wv = (WebView) findViewById(R.id.webView);
		
		WebSettings ws = wv.getSettings();
		ws.setJavaScriptEnabled(true);
		
		wv.addJavascriptInterface(this, "ExemploWebView");
		
		wv.loadUrl(url+"curso=AndroidBasico");
	}
	
	@JavascriptInterface
	public void getForm(String nome, String email, String senha){
		Log.i("Script", "Nome: "+nome);
		Log.i("Script", "Email: "+email);
		Log.i("Script", "Senha: "+senha);
	}
}
