package br.com.targettrust.otempo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import android.widget.TextView;

public class DetailActivity extends Activity {

	public static final String TAG = "DetailActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		String dia = getIntent().getExtras().getString("dia");
		String tempo = getIntent().getExtras().getString("tempo");
		String maxima = getIntent().getExtras().getString("maxima");
		String minima = getIntent().getExtras().getString("minima");
		String iuv = getIntent().getExtras().getString("iuv");
		
		
		((TextView)findViewById(R.id.dataText)).setText(dia);
		((TextView)findViewById(R.id.maximaText)).setText(maxima);
		((TextView)findViewById(R.id.minimaText)).setText(minima);
		((TextView)findViewById(R.id.iuvText)).setText(iuv);
		final String t = tempo; 
		
		new Thread() {
			public void run() {
				try{
					Bitmap img = null;
					try {
						URL url = new URL("http://img0.cptec.inpe.br/~rgrafico/icones_principais/tempo/icones/"+t+".png");
						HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
						InputStream input =  conexao.getInputStream();
						img = BitmapFactory.decodeStream(input);
					} catch(Exception e) {
						e.printStackTrace();
					}
					final Bitmap imgAux = img;
					handler.post(new Runnable() {
						public void run() {
							ImageView i = (ImageView)findViewById(R.id.imageView1);
							i.setImageBitmap(imgAux);
						}
					});
					}catch(Exception r){
					r.printStackTrace();
					
				}
			};
			
			
		}.start();
		
		
	}

	 public Handler handler = new Handler();
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
