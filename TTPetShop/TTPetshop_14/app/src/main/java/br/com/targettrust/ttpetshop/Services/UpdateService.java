/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */

package br.com.targettrust.ttpetshop.Services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import br.com.targettrust.ttpetshop.Recievers.ScreenReceiver;

public class UpdateService extends Service {

    private String TAG = UpdateService.class.getSimpleName();

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
             Log.v(TAG, "onStart: screenOff");
        	// Faça o que você precisa fazer aqui
         } else {
             Log.v(TAG, "onStart: screenOn");
             // Faça o que você precisa fazer aqui
         }
     }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
