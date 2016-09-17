package br.exemplorunonuithread;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class GetImgWeb {
	private static Bitmap img;
	
	public static void getImgWeb(final Activity atividade, final ImageView iv){
		new Thread(){
			public void run(){
				try{
					URL url = new URL("http://www.targettrust.com.br/img/header-logo_v2.png");
					HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
					InputStream input = conexao.getInputStream();
					img = BitmapFactory.decodeStream(input);
				}
				catch(IOException e){}
				
				atividade.runOnUiThread(new Runnable(){
					public void run(){
						iv.setImageBitmap(img);
					}
				});
			}
		}.start();
	}
}
