package br.com.targettrust.aulaservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TesteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context ctx, Intent intent) {

		Toast.makeText(ctx, "RECEIVER !!!", Toast.LENGTH_SHORT).show();
		
		
	}

}
