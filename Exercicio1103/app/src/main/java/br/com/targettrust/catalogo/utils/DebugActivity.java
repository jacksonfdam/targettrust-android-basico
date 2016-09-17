/**
 * 
 */
package br.com.targettrust.catalogo.utils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Jackson F. de A. Mafra <jacksonfdam@gmail.com>
 *
 */
public class DebugActivity extends Activity {
	private static final String TAG = DebugActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "DebugActivity.onCreate(): " + getClass().getSimpleName());

		//LOG DE MEMORIA
		Util.logHeap(this.getClass());


		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		Log.d(TAG, "DebugActivity.onStart(): " + getClass().getSimpleName());
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "DebugActivity.onResume(): " + getClass().getSimpleName());
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "DebugActivity.onPause(): " + getClass().getSimpleName());
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "DebugActivity.onStop(): " + getClass().getSimpleName());
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "DebugActivity.onDestroy(): " + getClass().getSimpleName());
		super.onDestroy();
	}
}
