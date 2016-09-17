package br.exemplosendimageforphp;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import br.exemplosendimageforphp.domain.WrapData;
import br.exemplosendimageforphp.util.HttpConnection;

public class MainActivity extends Activity {
	private WrapData wd;
	private ImageView imageView;
	private EditText etName;
	private EditText etEmail;
	private EditText etAge;
	private Button btTakePhoto;
	private Button btSdcardPhoto;
	private Button btSend;
	private static final int IMG_CAM = 1;
	private static final int IMG_SDCARD = 2;
	private String answer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		wd = new WrapData();
		
		imageView = (ImageView) findViewById(R.id.imageView);
		
		etName = (EditText) findViewById(R.id.etName);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etAge = (EditText) findViewById(R.id.etAge);
		
		btSend = (Button) findViewById(R.id.btSend);
		btTakePhoto = (Button) findViewById(R.id.btTakePhoto);
		btSdcardPhoto = (Button) findViewById(R.id.btSdcardPhoto);
	}
	
	// CALL IN CAM
		public void callIntentImgCam(View view){
			File file = new File(android.os.Environment.getExternalStorageDirectory(), "img.png");
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			startActivityForResult(intent, IMG_CAM);
		}
		
	// IMG IN SDCARD
		public void callIntentImgSDCard(View view){
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
			startActivityForResult(intent, IMG_SDCARD);
		}
		
	// IMG IN SDCARD
		public void sendData(View view){
			enableViews(false);
			
			wd.setUrl("http://localhost/ctrl/CtrlForm.php");
			wd.setMethod("save-form");
			wd.setName(etName.getText().toString());
			wd.setEmail(etEmail.getText().toString());
			wd.setAge(Integer.parseInt(etAge.getText().toString()));
			
			new Thread(){
				public void run(){
					answer = HttpConnection.getSetDataWeb(wd);
					
					runOnUiThread(new Runnable(){
						public void run(){
							enableViews(true);
							try{
								answer = Integer.parseInt(answer) == 1 ? "Form enviado com sucesso!" : "FAIL!";
								Toast.makeText(MainActivity.this, answer, Toast.LENGTH_SHORT).show();
							}
							catch(NumberFormatException e){ e.printStackTrace(); }
						}
					});
				}
			}.start();
		}
		
	public void enableViews(boolean status){
		btTakePhoto.setEnabled(status);
		btSdcardPhoto.setEnabled(status);
		btSend.setEnabled(status);
		etName.setEnabled(status);
		etEmail.setEnabled(status);
		etAge.setEnabled(status);
		
		btSend.setText(status ? "Enviar" : "Enviando...");
	}
		
		
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		File file = null;
		
		if(data != null && requestCode == IMG_SDCARD && resultCode == RESULT_OK){
			Uri img = data.getData();
			String[] cols = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(img, cols, null, null, null);
			cursor.moveToFirst();
			
			int indexCol = cursor.getColumnIndex(cols[0]);
			String imgString = cursor.getString(indexCol);
			cursor.close();
			
			file = new File(imgString);
			if(file != null){
				wd.getImage().setResizedBitmap(file, 300, 300);
				wd.getImage().setMimeFromImgPath(file.getPath());
			}
		}
		else if(requestCode == IMG_CAM && resultCode == RESULT_OK){
			file = new File(android.os.Environment.getExternalStorageDirectory(), "img.png");
			if(file != null){
				wd.getImage().setResizedBitmap(file, 300, 300);
				wd.getImage().setMimeFromImgPath(file.getPath());
			}
		}
		
		
		if(wd.getImage().getBitmap() != null){
			imageView.setImageBitmap(wd.getImage().getBitmap());
		}
	}
}
