package br.exemplowebview;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);
		
		for(int i = 0; i < 3; i++){
			WebView wv = new WebView(this); //(WebView) findViewById(R.id.webView1);
			
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			wv.setLayoutParams(lp);
			
			WebSettings ws = wv.getSettings();
			ws.setJavaScriptEnabled(true);
			ws.setSupportZoom(false);
			
			//wv.loadUrl("http://www.targettrust.com.br/img/header-logo_v2.png");
		
			String html = "<html>";
			html += "<body>";
			html += "<img src=\"http://www.targettrust.com.br/img/header-logo_v2.png\" style=\"float: left; display: block; margin-right: 10px;\" />";
			html += "<h3 id=\"h3\" style=\"float: left;\">Texto auxiliar "+(i+1)+"</h3>";
			html += "<script type=\"text/javascript\">";
			html += "document.getElementById('h3').style.color = '#ff0000';";
			html += "</script></body></html>";
			
			wv.loadData(html, "text/html", "UTF-8");
			
			ll.addView(wv);
		}
	}
}
