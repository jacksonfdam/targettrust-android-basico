package br.exemploservicegw;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Toast;
import br.exemploservicegw.ServicoIntent.Controller;

public class MainActivity extends Activity implements ServiceConnection {
	private CountListener cl;
	private ServiceConnection connection;
	private ResultReceiverListener rr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		connection = this;
	
		bindService(new Intent("SERVICO_INTENT"), connection, Context.BIND_AUTO_CREATE); // Context.BIND_AUTO_CREATE
	}
	
	public void startService(View view){
		
		rr = null;
		rr = new ResultReceiverListener(null);
		
		Intent it = new Intent("SERVICO_INTENT");
		it.putExtra("receiver", rr);
		
		startService(it);
		
	}
	
	public void stopService(View view){
		Intent it = new Intent("SERVICO_INTENT");
		it.putExtra("desligar", 1);
		startService(it);
		//stopService(it);
		//unbindService(connection);
	}
	
	public void getCount(View view){
		Toast.makeText(this, "COUNT: "+cl.getCount(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		// TODO Auto-generated method stub
		Controller c = (Controller) service;
		cl = c.getCountListener();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	private class ResultReceiverListener extends ResultReceiver{
		
		public ResultReceiverListener(Handler handler){
			super(handler);
		}
		
		@Override
		protected void onReceiveResult(int resultCode, Bundle bundle){
			if(resultCode == 1){
				final int count = bundle.getInt("count");
				
				runOnUiThread(new Runnable(){
					public void run(){
						Toast.makeText(MainActivity.this, "COUNT: "+count, Toast.LENGTH_SHORT).show();
					}
				});
			}
		}
	}
}
