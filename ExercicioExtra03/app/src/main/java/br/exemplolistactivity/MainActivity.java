package br.exemplolistactivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] atividades = new String[]{"Atividade 1", "Atividade 2", "Sair"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, atividades);
		
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		Intent intent;
		
		switch(position){
			case 0:
				intent = new Intent(this, Atividade1.class);
				startActivity(intent);
				break;
			case 1:
				intent = new Intent(this, Atividade2.class);
				startActivity(intent);
				break;
			default:
				finish();
		}
	}
}
