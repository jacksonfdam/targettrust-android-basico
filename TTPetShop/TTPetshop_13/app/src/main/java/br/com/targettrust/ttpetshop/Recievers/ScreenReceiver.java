package br.com.targettrust.ttpetshop.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



public class ScreenReceiver extends BroadcastReceiver {
	 public static boolean wasScreenOn = true;
	 
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                // Faça o que você precisa fazer aqui
	            wasScreenOn = false;
	        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                // Faça o que você precisa fazer aqui
	            wasScreenOn = true;
	        }
	    }
}
