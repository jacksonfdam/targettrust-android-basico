package br.exemplothreadprincipal;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void baixarImagemWeb(View view){
		new Thread(){
			public void run(){
				try {
					
					URL url = new URL("http://www.targettrust.com.br/img/header-logo_v2.png");
					HttpURLConnection connection;
					connection = (HttpURLConnection) url.openConnection();
					connection.setDoInput(true);
			        connection.connect();
			        InputStream input = connection.getInputStream();
			        final Bitmap imagem = BitmapFactory.decodeStream(input);
			        
			        Log.i("livro", "baixou imagem.");
			        
			        final ImageView iv = (ImageView) findViewById(R.id.imagem);
			        
			        handler.post(new Runnable(){
			        	public void run(){
			        		iv.setImageBitmap(imagem);
			        	}
			        });
				}
				catch(MalformedURLException e) {
					e.printStackTrace();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
