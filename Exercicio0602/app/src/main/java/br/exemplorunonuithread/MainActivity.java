package br.exemplorunonuithread;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	//private Handler handler;
	private Bitmap img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//handler = new Handler();
	}
	
	public void baixarImg(View view){
		GetImgWeb.getImgWeb(this, (ImageView) findViewById(R.id.imageView1));
	}
}
