package br.exemplosavedinstancestate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
	private Handler handler = new Handler();
	private ArrayList<Img> imgs = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		if(savedInstanceState != null){
			ListaImgs li = (ListaImgs) savedInstanceState.getSerializable(ListaImgs.KEY);
			imgs = li.imgs;
		}
		
		
		if(imgs == null){
			imgs = new ArrayList<Img>();
			Log.i("Script", "Entrei 1");
			loadImg();
		}
		else{
			Log.i("Script", "Entrei 2");
			buildImgs();
		}
	}
	
	
	// BAIXA AS IMGS DA WEB
		public void loadImg(){
			
			new Thread(){
				public void run(){
					
					try{
						for(int i = 0; i < 30; i++){
							URL url = new URL("http://www.targettrust.com.br/img/favicon_72x72_iPad.png");
							HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
							InputStream input = conexao.getInputStream();
							imgs.add(new Img(BitmapFactory.decodeStream(input)));
						}
					}
					catch(IOException e){}
					
					handler.post(new Runnable(){
						public void run(){
							buildImgs();
						}
					});
				}
			}.start();
		}
	
	
	// COLOCA AS IMGS NO LAYOUT
		public void buildImgs(){
			LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout1);
			
			for(int i = 0; i < imgs.size(); i++){
				ImageView iv = new ImageView(getBaseContext());
				iv.setImageBitmap(imgs.get(i).bitmap);
				ll.addView(iv);
			}
		}
		
	@Override
	protected void onSaveInstanceState(Bundle bundle){
		super.onSaveInstanceState(bundle);
		
		bundle.putSerializable(ListaImgs.KEY, new ListaImgs(imgs));
	}
}
