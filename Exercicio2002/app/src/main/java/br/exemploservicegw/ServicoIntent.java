package br.exemploservicegw;

import br.exemploservicegw.ServicoConexao.Controller;
import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

public class ServicoIntent extends IntentService implements CountListener {
	private int count;
	private boolean ativo;
	private boolean stopAll;
	private ResultReceiver rr;
	
	private Controller controller = new Controller();
	public class Controller extends Binder {
		public CountListener getCountListener(){
			return(ServicoIntent.this);
		}
	}
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return controller;
	}
	
	
	public ServicoIntent(){
		super("ServicoIntentThread");
	
		count = 0;
		ativo = true;
		stopAll = true;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		
		Bundle b = intent.getExtras();
		if(b != null){
			int desligar = b.getInt("desligar");
			if(desligar == 1){
				stopAll = false;
			}
			else{
				stopAll = true;
				rr = intent.getParcelableExtra("receiver");
			}
		}
		
		return(super.onStartCommand(intent, flags, startId));
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		while(stopAll && ativo && count < 50){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			count++;
			Log.i("Script", "COUNT: "+count);
			
			Bundle b = new Bundle();
			b.putInt("count", count);
			rr.send(1, b);
		}
		
		ativo = true;
		count = 0;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
