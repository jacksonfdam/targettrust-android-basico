package br.com.targettrues.aulacamera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView imageView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageView = (ImageView) findViewById(R.id.imageView1);
        
        
        if (savedInstanceState != null) {
        	
        	Bitmap bitmap = savedInstanceState.getParcelable("bitmap");
        	imageView.setImageBitmap(bitmap);
        	
        }
        
        
    }

    public void fotoMemoria (View v) {
    	
    	Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivityForResult(it, 1);
		
    }
    
    public void fotoArquivo (View v) {

    	Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
    	
    	File arquivoFoto = new File(
    			Environment.getExternalStorageDirectory(),
    			"Foto1.jpg");
	    
    	it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
   	
		startActivityForResult(it, 2);

    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	if (requestCode == 1) {
    		if (resultCode == RESULT_OK) {
				// acessar o bitmap que vem da câmera
    			Bitmap bitmap = (Bitmap) data.getParcelableExtra("data");
    			
    			// setar o bitmap na imageView
    			imageView.setImageBitmap(bitmap);
    		}
    	
    	} else if (requestCode == 2) {
    		if (resultCode == RESULT_OK) {
    			
    			File arquivoFoto = new File(
    	    			Environment.getExternalStorageDirectory(),
    	    			"Foto1.jpg");
    			
    			Bitmap bitmap = BitmapFactory.decodeFile(
    					arquivoFoto.getAbsolutePath());
    			
    			bitmap = ThumbnailUtils.extractThumbnail(bitmap, 100, 100);
    			
    			imageView.setImageBitmap(bitmap);
    			
    			
    		}
    	}
    	
    }
   
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);

    	Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable())
    			.getBitmap();
    	
    	outState.putParcelable("bitmap", bitmap);
    
    }
    
    
}
