package com.example.exemplosdcardimagedialog;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	public static final int IMAGEM_INTERNA = 12;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void pegarImg(View view){
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, IMAGEM_INTERNA);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent){
		if(requestCode == IMAGEM_INTERNA){
			if(resultCode == RESULT_OK){
				
				Uri imagemSelecionada = intent.getData();
				
				String[] colunas = {MediaStore.Images.Media.DATA};
				
				Cursor cursor = getContentResolver().query(imagemSelecionada, colunas, null, null, null);
				cursor.moveToFirst();
				
				int indexColuna = cursor.getColumnIndex(colunas[0]);
				String pathImg = cursor.getString(indexColuna);
				cursor.close();
				
				Bitmap bitmap = BitmapFactory.decodeFile(pathImg);
				ImageView iv = (ImageView) findViewById(R.id.iv);
				iv.setImageBitmap(bitmap);
			}
		}
	}
}
