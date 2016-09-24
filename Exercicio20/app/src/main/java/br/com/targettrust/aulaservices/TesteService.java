package br.com.targettrust.aulaservices;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class TesteService extends Service {
	
	private static final String TAG = "TesteService";

	int cont = 0;
	
	Handler handler;
	
	Runnable r = new Runnable() {
		@Override
		public void run() {
			
			// envio do contador para a activity
			Intent intent = new Intent("RECEIVER_CONTADOR");
			intent.putExtra("contador", cont);
			sendBroadcast(intent);
			
			
			Log.d(TAG, "dentro da runnable.... " + cont);
			cont++;
			
			// cria notification
			criaNotification(cont);

			if (cont == 5) {
				
				// envio de mensagem de broadcast
				Intent i = new Intent("TESTE_RECEIVER");
				sendBroadcast(i);
				
				// manda parar o serviço
				stopSelf();
				
				// cria notification
				criaNotification(10);
				
			} else {			
				handler.postDelayed(r, 3000);
			}
			
		}
	};

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate");
		handler = new Handler();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand - startID = " + startId + intent.getExtras());

		handler.post(r);
		
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
		
		handler.removeCallbacks(r);
		
	}
	
	public void criaNotification(int number) {
		
		
		Notification notification = new Notification(R.drawable.ic_launcher,
				"Notificação criada!!!", 
				System.currentTimeMillis());
		
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				this, 0, intent, 0);
		
		notification.setLatestEventInfo(this, 
				"Título da Notificação", 
				"Textinho de detalhes da notificação.", 
				pendingIntent);
		
		notification.sound = RingtoneManager.getDefaultUri(
				RingtoneManager.TYPE_NOTIFICATION);
		
		notification.number = number;
		
		NotificationManager notificationManager = 
				(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(number, notification);

		
		
	}
    
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
