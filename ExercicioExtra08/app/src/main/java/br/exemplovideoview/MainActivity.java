package br.exemplovideoview;

import java.io.File;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*File sdcard = android.os.Environment.getExternalStorageDirectory();
		File file = new File(sdcard, "video.3gp");
		*/
		VideoView video = new VideoView(this);
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);
		ll.addView(video);
		
		//String src = file.getAbsolutePath();
		//video.setVideoPath(src);
		
		Uri src = Uri.parse("http://localhost/video.3gp");
		video.setVideoURI(src);
		
		video.setMediaController(new MediaController(this));
	}

}
