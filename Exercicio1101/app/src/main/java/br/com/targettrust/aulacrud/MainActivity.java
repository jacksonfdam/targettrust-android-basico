package br.com.targettrust.aulacrud;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	
	SQLiteDatabase db;
	EditText etDescricao, etCodigo, etPreco, etId;
	TextView tvResultado;
	ListView lista;
	ArrayList<ContentValues> produtos = new ArrayList<ContentValues>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        etDescricao = (EditText) findViewById(R.id.etDescricao);
        etCodigo = (EditText) findViewById(R.id.etCodigo);
        etPreco = (EditText) findViewById(R.id.etPreco);
        etId = (EditText) findViewById(R.id.etId);
        
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        lista = (ListView) findViewById(R.id.listView1);
        openDB();
        
        lista.setOnItemClickListener(new OnItemClickListener(){
        		public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
        			ContentValues cv = (ContentValues) produtos.get(position);
        			Toast.makeText(getBaseContext(), "Selecionado: " + cv.getAsString("descricao"), Toast.LENGTH_SHORT).show();
        		}
        });
        
        
    }

    private void openDB() {
    	Log.d(TAG , "criando ou abrindo o banco...");
    	db = openOrCreateDatabase("db_teste.db", MODE_PRIVATE, null);
        
        StringBuilder strb = new StringBuilder();
        
        strb.append(" CREATE TABLE IF NOT EXISTS PRODUTOS (        ");
        strb.append(" id         integer primary key autoincrement, ");
        strb.append(" descricao  varchar(30),                       ");
        strb.append(" codigo     varchar(100),                      ");
        strb.append(" preco      double )                           ");
        
        db.execSQL(strb.toString());
    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	db.close();
    }
    
    public void insert (View v) {
    	
    	String descricao = etDescricao.getText().toString();
    	String codigo = etCodigo.getText().toString();
    	String preco = etPreco.getText().toString();
    	
    	ContentValues cv = new ContentValues();
    	cv.put("descricao", descricao);
    	cv.put("codigo", codigo);
    	cv.put("preco", preco);
    	
    	long rowId = db.insert("PRODUTOS", null, cv);
    	
    	if(rowId > 0) {
    		tvResultado.setText("INSERT OK [" + rowId + "]");
    	}
    	
    	limpar();
    }
    
    private void limpar() {
    	etDescricao.setText("");
    	etCodigo.setText("");
    	etPreco.setText("");
    }
    
    public void selectAll (View v) {
    	
    	Cursor c = db.query("PRODUTOS", 
    			new String[]{"id", "descricao", "codigo", "preco"}, 
    						null, null, null, null, null);
    	
    	StringBuilder str = new StringBuilder();
    	
    	while (c.moveToNext()) {
    		
    		str.append( c.getString(0) ).append(", ");
    		str.append( c.getString(1) ).append(", ");
    		str.append( c.getString(2) ).append(", ");
    		str.append( c.getString(3) ).append("\n");
    		
        	ContentValues cv = new ContentValues();
        	cv.put("descricao", c.getString(1));
        	cv.put("codigo", c.getString(2));
        	cv.put("preco", c.getString(3));
        	produtos.add(cv);
    		
    	}
    	
//    	SimpleCursorAdapter c_adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, c, 
//    			new String[]{"descricao"}, new int[] {android.R.id.text1});
//    		
//    	};
    	
    	ArrayAdapter<ContentValues> adapter = new ArrayAdapter<ContentValues>(this, android.R.layout.simple_list_item_1, produtos);
    	
    	lista.setAdapter(adapter);
    	
    	
    	
    	
    	tvResultado.setText(str.toString());
    	
    }
    
    public void selectById (View v) {
    	
    	String id = etId.getText().toString();

//    	Cursor c = db.rawQuery("SELECT count(*) " +
//    			"FROM PRODUTOS ",
//    			null);

    	Cursor c = db.rawQuery("SELECT id, descricao, codigo, preco " +
    			"FROM PRODUTOS " +
    			"WHERE id = ?",
    			new String[] {id});
    	
//    	Cursor c = db.query("PRODUTOS", 
//    			new String[]{"id", "descricao", "codigo", "preco"},
//    			"id = ?", new String[] {id}, null, null, null);
//    	
    	if (c.moveToFirst()) {
    		
//    		tvResultado.setText(c.getString(0));
    		etDescricao.setText( c.getString( c.getColumnIndex("descricao") ) );
    		etCodigo.setText( c.getString( c.getColumnIndex("codigo") ) );
    		etPreco.setText( c.getString( c.getColumnIndex("preco") ) );
    		
    	} else {
    		limpar();
    	}
    	
    	c.close();
    	
    }
     
    public void update (View v) {
    	
    	String descricao = etDescricao.getText().toString();
    	String codigo = etCodigo.getText().toString();
    	String preco = etPreco.getText().toString();
    	
    	ContentValues cv = new ContentValues();
    	cv.put("descricao", descricao);
    	cv.put("codigo", codigo);
    	cv.put("preco", preco);
    	
    	String id = etId.getText().toString();

    	int rows = db.update("PRODUTOS", cv, "id = ?", new String[]{id});
    	
    	if (rows > 0) {
    		tvResultado.setText("UPDATE OK [" + rows + "]");
    	} else { 
    		tvResultado.setText("sem alterações");
    	}
    	
    }
    
    public void delete (View v) {
    	
    	String id = etId.getText().toString();
    	
    	int rows = db.delete("PRODUTOS", "id = ?", new String[]{id});
    	
    	if (rows > 0) {
    		tvResultado.setText("DELETE OK [" + rows + "]");
    	} else { 
    		tvResultado.setText("sem alterações");
    	}
    }
    
}
