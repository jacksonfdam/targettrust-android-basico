package br.com.targettrust.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.suelengc.devfest.R;
import br.com.targettrust.example0.MainActivity;
import br.com.targettrust.fragments.example0.MainFragments;
import br.com.targettrust.fragments.example1.Example1Main;

public class MainListExamplesActivity extends Activity implements OnItemClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_list_examples);

		List<ItemExample> listExamples = new ArrayList<ItemExample>();
		listExamples = ItemExample.getListExamples();
		
		AdapterItemExample adapterItemExample = new AdapterItemExample(this, listExamples);
		
		ListView list = (ListView) findViewById(R.id_main_list_example.list);
		list.setAdapter(adapterItemExample);
		list.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		ItemExample item = (ItemExample) adapter.getAdapter().getItem(position);

		Toast.makeText(this, "Item selecionado: " + item.getId(), Toast.LENGTH_SHORT).show();

		Intent intent = null;
		
		switch (item.getId()) {
		case 0:
			intent = new Intent(this, MainActivity.class);
			break;
		case 1:
			intent = new Intent(this, MainFragments.class);
			break;
		case 2:
			intent = new Intent(this, Example1Main.class);
			break;
		}
		
		if (intent != null) 
			startActivity(intent);

	}
}
