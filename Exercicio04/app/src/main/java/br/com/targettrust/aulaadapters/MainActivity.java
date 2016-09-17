package br.com.targettrust.aulaadapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView lv;
	Spinner sp;
	
	Toast t;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView1);
        sp = (Spinner) findViewById(R.id.spinner1);
        
        populaSpinner();
//        adapterComArray();
//        adapterComList();
//        adapterComRadio();
//        adapterComCheck();
        //adapterSimple();
        adapterBase();
        
    }

    private void populaSpinner() {
    	
    	List<String> list = new ArrayList<String>();
    	
    	list.add("Android");
    	list.add("iPhone");
    	list.add("Windows Mobile");
    	list.add("Symbian");
    	list.add("Blackberry");
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(
    			getBaseContext(), 
    			android.R.layout.simple_spinner_item,
    			list);
    	
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	
    	sp.setAdapter(adapter);

    }
    
    private void adapterComArray() {
    	
    	String[] array = new String[]{"Android", "iPhone", "Windows", "Symbian", "BlackBerry"};
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(
    			getBaseContext(), 
    			android.R.layout.simple_list_item_1,
    			array);
    	
    	lv.setAdapter(adapter);
    	
    }
    
    private void adapterComList() {
    	
    	List<String> list = new ArrayList<String>();
    	
    	list.add("Android");
    	list.add("iPhone");
    	list.add("Windows Mobile");
    	list.add("Symbian");
    	list.add("Blackberry");
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(
    			getBaseContext(), 
    			android.R.layout.simple_list_item_1,
    			list);
    	
    	lv.setAdapter(adapter);
    	
    }

    private void adapterComRadio() {
    	
    	List<String> list = new ArrayList<String>();
    	
    	list.add("Android");
    	list.add("iPhone");
    	list.add("Windows Mobile");
    	list.add("Symbian");
    	list.add("Blackberry");
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(
    			getBaseContext(), 
    			android.R.layout.simple_list_item_single_choice,
    			list);
    	
    	lv.setAdapter(adapter);
    	lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    
    }

    private void adapterComCheck() {
    	
    	List<String> list = new ArrayList<String>();
    	
    	list.add("Android");
    	list.add("iPhone");
    	list.add("Windows Mobile");
    	list.add("Symbian");
    	list.add("Blackberry");
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(
    			getBaseContext(), 
    			android.R.layout.simple_list_item_multiple_choice,
    			list);
    	
    	lv.setAdapter(adapter);
    	lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    
    }

    public void showSelectedMultiple(View v) {

    	SparseBooleanArray sparse = lv.getCheckedItemPositions();
    	for (int i = 0; i < sparse.size(); i++) {
			if( sparse.valueAt(i) ) {
				
				String st = (String) lv.getAdapter().getItem(sparse.keyAt(i));

				Toast.makeText(getBaseContext(),
		    			"Selecionado: " + st, 
		    			Toast.LENGTH_SHORT).show();
				
			}
		}
    	
    }
    	
    public void showSelected(View v) {
    	
    	int pos = lv.getCheckedItemPosition();
    	String st = (String) lv.getAdapter().getItem(pos);
    	
    	Toast.makeText(getBaseContext(),
    			"Selecionado: " + st, 
    			Toast.LENGTH_SHORT).show();
    	
    }
    
    private void adapterSimple() {
    	
    	List< Map<String, String> > list = new ArrayList< Map <String, String>>();
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("nome", "João");
    	map.put("fone", "99887766");
    	list.add(map);
    	
    	map = new HashMap<String, String>();
    	map.put("nome", "Maria");
    	map.put("fone", "88776655");
    	list.add(map);
    	
//    	SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), 
//    			list, 
//    			android.R.layout.simple_list_item_2, 
//    			new String[] {"nome", "fone"}, 
//    			new int[] {android.R.id.text1, android.R.id.text2});

    	SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), 
    			list, 
    			R.layout.linha, 
    			new String[] {"nome", "fone"}, 
    			new int[] {R.id.textView1, R.id.textView2});

    	
    	lv.setAdapter(adapter);
    	
    }
    
    private void adapterBase() {
    	
    	List<ProdutoVO> list = new ArrayList<ProdutoVO>();
    	
    	list.add(new ProdutoVO("produto 1", 12.99));
    	list.add(new ProdutoVO("produto 222", 120.99));
    	list.add(new ProdutoVO("produto 33333", 10.00));
    	
    	ProdutoAdapter adapter = new ProdutoAdapter(getBaseContext(), list);
    	
    	lv.setAdapter(adapter);
    	
    	lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				ProdutoVO produto = (ProdutoVO) adapter.getItemAtPosition(position);
				Toast.makeText(getBaseContext(), 
						"Selecionado: " + produto.getNome(), 
						Toast.LENGTH_SHORT)
						.show();
			}
		});

    	lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int position,
					long id) {
				ProdutoVO produto = (ProdutoVO) adapter.getItemAtPosition(position);
				
				if (t != null) {
					t.cancel();
				}
				
				t = Toast.makeText(getBaseContext(), 
						"LOOOONG: " + produto.getNome(), 
						Toast.LENGTH_LONG);
				
				t.show();
				
				
				
				
				return true;
			}
		});
    	
    }
    
    
}
