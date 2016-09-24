package br.exemploservicegw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastTest extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("Script", "onReceive()");
		intent = new Intent("SERVICO_TEST");
		context.startService(intent);
	}

}
