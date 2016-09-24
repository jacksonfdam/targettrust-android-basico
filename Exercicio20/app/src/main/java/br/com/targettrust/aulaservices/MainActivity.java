package br.com.targettrust.aulaservices;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tvCont;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvCont = (TextView) findViewById(R.id.textView);
        
        registerReceiver(receiver, new IntentFilter("RECEIVER_CONTADOR"));
    }

    protected void onDestroy() {
    	super.onDestroy();
    	unregisterReceiver(receiver);
    }
    
    BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			int cont = intent.getIntExtra("contador", -1);
			tvCont.setText("Contador: " + cont);
		}
	};
    
    
    
    public void start (View v) {
    	
//    	Intent i = new Intent(getBaseContext(), TesteService.class);
    	Intent i = new Intent("TESTE_SERVICO");
    	i.putExtra("parametro", "PAR!!!");
    	startService(i);
    	
    }


    public void stop (View v) {

//    	Intent i = new Intent(getBaseContext(), TesteService.class);
    	Intent i = new Intent("TESTE_SERVICO");
    	stopService(i);

    }

}
