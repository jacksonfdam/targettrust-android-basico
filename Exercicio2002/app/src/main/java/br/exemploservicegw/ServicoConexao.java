package br.exemploservicegw;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ServicoConexao extends Service implements CountListener {
	private int count;
	private boolean active;
	
	private Controller controller = new Controller();
	
	public class Controller extends Binder {
		public CountListener getCountListener(){
			return(ServicoConexao.this);
		}
	}
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return controller;
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
		//setThread();
		Log.i("Script", "onCreate()");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		Log.i("Script", "onStartCommand()");
		setThread();
		return(super.onStartCommand(intent, flags, startId));
	}
	
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		active = false;
	}
	
	
	public void setThread(){
		count = 0;
		active = true;
		new Thread(){
			public void run(){
				while(active && count < 100){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					count++;
					Log.i("Script", "count: "+count);
				}
			}
			
		}.start();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
}
