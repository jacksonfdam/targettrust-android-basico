package br.exemplolistview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String[] atividades = new String[]{"Atividade 1", "Atividade 2"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, atividades);
		
		ListView lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(chamaAtividades());
	}
	
	public OnItemClickListener chamaAtividades(){
		return(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				Intent intent;
				
				switch(position){
					case 0:
						intent = new Intent(getBaseContext(), Atividade1.class);
						startActivity(intent);
						break;
					case 1:
						intent = new Intent(getBaseContext(), Atividade2.class);
						startActivity(intent);
						break;
				}
			}
			
		});
	}
	
	public void sair(View view){
		finish();
	}
}
