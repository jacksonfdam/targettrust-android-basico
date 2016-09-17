package br.com.targettrust.aulaasync;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;
	ProgressBar progress;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView1);
        progress = (ProgressBar) findViewById(R.id.progressBar1);
    }

    public void async(View v) {
    	
    	new TrabalhoParalelo().execute(5);
    	
    }

    class TrabalhoParalelo extends AsyncTask<Integer, Integer, String> {

    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		progress.setVisibility(View.VISIBLE);
    	}
    	
		@Override
		protected String doInBackground(Integer... params) {
			for (int i = 0; i < params[0]; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i);
			}
			Log.d("BACKGROUND", "fim do doInBrackground !!!");
			return "FIM";
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			tv.setText("Etapa " + values[0]);
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			progress.setVisibility(View.INVISIBLE);

			tv.setText(result);
		}
    	
    }
    
    
    
    
}
