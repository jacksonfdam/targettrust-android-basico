package br.com.targettrust.aulaciclodevida;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.d(TAG, "fim do onCreate");
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }


    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    
    	switch (item.getItemId()) {
		case R.id.item1:
			Toast.makeText(getBaseContext(), "Item 1", Toast.LENGTH_SHORT).show();
			break;

		case R.id.item2:
			Toast.makeText(getBaseContext(), "Item 2", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

    	return true;
    	
    }
    
    
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}

    @Override
    protected void onStart() {
    	super.onStart();

    	Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
    	super.onStop();
    	Log.d(TAG, "onStop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Log.d(TAG, "onDestroy");
    }
    
    
    
    public void proxima (View v) {
    	
    	Intent i = new Intent(getBaseContext(), SegundaActivity.class);
    	
    	i.putExtra("nome", "João");
    	i.putExtra("idade", 61);
    	
    	startActivity(i);
    	
        Log.d(TAG, "chamei a segunda activity");

    	
    }
    
}
