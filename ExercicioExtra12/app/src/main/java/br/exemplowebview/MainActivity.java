package br.exemplowebview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.graphics.Bitmap;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ProgressBar pb = new ProgressBar(this);
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.CENTER;
		pb.setLayoutParams(lp);
		
		final FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayout);
		
		final WebView wv = (WebView) findViewById(R.id.webView);
		wv.loadUrl("http://localhost/index.php");
	
	
		wv.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon){
				/*ProgressBar pb = (ProgressBar) findViewById(R.id.progress);
				pb.setVisibility(View.VISIBLE);*/
				fl.addView(pb);
			}
			
			@Override
			public void onPageFinished(WebView view, String url){
				//ProgressBar pb = (ProgressBar) findViewById(R.id.progress);
				//pb.setVisibility(View.INVISIBLE);
				fl.removeView(pb);
				wv.setVisibility(View.VISIBLE);
			}
		});
	}
}
