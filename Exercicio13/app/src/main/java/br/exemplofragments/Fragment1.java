package br.exemplofragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment1 extends Fragment {
	private int contador = 0;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		Log.i("Script", "-> onAttach()");
		Log.i("Script", "HOST -> "+activity.getClass().getName());
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i("Script", "-> onCreate()");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		Log.i("Script", "-> onCreateView()");
		
		
		/*if(savedInstanceState != null){
			contador = savedInstanceState.getInt("contador");
		}*/
		Log.i("Script", "CONTADOR -> "+contador);
		
		
		View view = inflater.inflate(R.layout.layout_frag_1, null);
		
		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText("Fragment 1");
		//
		setRetainInstance(true);
		return(view);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		Log.i("Script", "-> onActivityCreated()");
	}
	
	@Override
	public void onStart(){
		super.onStart();
		Log.i("Script", "-> onStart()");
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Log.i("Script", "-> onResume()");
		contador++;
	}
	
	@Override
	public void onPause(){
		super.onPause();
		Log.i("Script", "-> onPause()");
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.i("Script", "-> onStop()");
	}
	
	@Override
	public void onDestroyView(){
		super.onDestroyView();
		Log.i("Script", "-> onDestroyView()");
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.i("Script", "-> onDestroy()");
	}
	
	@Override
	public void onDetach(){
		super.onDetach();
		Log.i("Script", "-> onDetach()");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		Log.i("Script", "-> onSaveInstanceState()");
	}
	
	
	
	public void alteraTextView(String texto){
		TextView tv = (TextView) getView().findViewById(R.id.textView1);
		tv.setText(texto);
	}
}
