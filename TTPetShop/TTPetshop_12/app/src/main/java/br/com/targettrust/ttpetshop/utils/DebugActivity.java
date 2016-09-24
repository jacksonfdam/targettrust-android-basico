package br.com.targettrust.ttpetshop.utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

/**
 * Created by instrutor on 15/04/2015.
 */
public class DebugActivity  extends ActionBarActivity {
    Utils util;
    private static final String TAG = DebugActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "checkpoint on onCreate(): ");

        util = new Utils(this);
        //LOG DE MEMORIA
        util.logHeap(this.getClass());


        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.v(TAG, "checkpoint on onStart(): ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "checkpoint on onResume(): ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v(TAG, "checkpoint on onPause(): ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "checkpoint on onStop(): ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "checkpoint on onDestroy(): ");
        super.onDestroy();
    }
}
