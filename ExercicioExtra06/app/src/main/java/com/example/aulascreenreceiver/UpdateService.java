package com.example.aulascreenreceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class UpdateService extends Service {
	 @Override
     public void onCreate() {
         super.onCreate();
         // REGISTRO DO RECEPTOR que lida com tela Ligada e tela desligada
         IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
         filter.addAction(Intent.ACTION_SCREEN_OFF);
         BroadcastReceiver mReceiver = new ScreenReceiver();
         registerReceiver(mReceiver, filter);
     }

     @Override
     public void onStart(Intent intent, int startId) {
         boolean screenOn = intent.getBooleanExtra("screen_state", false);
         if (!screenOn) {
        	// Faça o que você precisa fazer aqui
         } else {
        	// Faça o que você precisa fazer aqui
         }
     }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
