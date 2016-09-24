package br.exemplonotification;

import java.net.URI;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void gerarNotificacao(View view){
		
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, Atividade2.class), 0);
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setTicker("Ticker Texto");
		builder.setContentTitle("Título");
		//builder.setContentText("Descrição");
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		builder.setContentIntent(p);
		
		NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
		String [] descs = new String[]{"Descrição 1", "Descrição 2", "Descrição 3", "Descrição 4"};
		for(int i = 0; i < descs.length; i++){
			style.addLine(descs[i]);
		}
		builder.setStyle(style);
		
		Notification n = builder.build();
		n.vibrate = new long[]{150, 300, 150, 600};
		n.flags = Notification.FLAG_AUTO_CANCEL;
		nm.notify(R.drawable.ic_launcher, n);
		
		try{
			Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone toque = RingtoneManager.getRingtone(this, som);
			toque.play();
		}
		catch(Exception e){}
	}
}
