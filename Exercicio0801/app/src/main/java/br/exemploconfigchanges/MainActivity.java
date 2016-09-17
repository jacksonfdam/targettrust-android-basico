package br.exemploconfigchanges;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i("Script", "onCreate()");
		
		// WEBVIEW
			WebView wv = (WebView) findViewById(R.id.webView1);
			WebSettings ws = wv.getSettings();
			ws.setJavaScriptEnabled(true);
			wv.loadUrl("http://localhost/mais-um-teste-mibec");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("Script", "onStart()");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("Script", "onResume()");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i("Script", "onSaveInstanceState()");
	}
	
	
	@Override
	public void onConfigurationChanged(Configuration novasConfig){
		super.onConfigurationChanged(novasConfig);
		Log.i("Script", "onConfigurationChanged()");
		
		LinearLayout rodape = (LinearLayout) findViewById(R.id.rodape);
		
		if(novasConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
			rodape.setVisibility(View.GONE);
		}
		else{
			rodape.setVisibility(View.VISIBLE);
		}
	}
}
