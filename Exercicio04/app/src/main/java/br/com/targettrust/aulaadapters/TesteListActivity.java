package br.com.targettrust.aulaadapters;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class TesteListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
    	String[] array = new String[]{"Android", "iPhone", "Windows", "Symbian", "BlackBerry"};
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(
    			getBaseContext(), 
    			android.R.layout.simple_list_item_1,
    			array);
		
	
		setListAdapter(adapter);
	}
	
}
