package br.com.targettrust.catalogo.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import br.com.targettrust.catalogo.R;
import br.com.targettrust.catalogo.utils.DebugActivity;

public class MainActivity extends DebugActivity {

	SQLiteDatabase db;
	ListView lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		db = openOrCreateDatabase("Catalogo.db", MODE_PRIVATE, null);
		lista = (ListView)findViewById(R.id.lista);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void loadData() {

		Cursor c = db.query("PRODUTOS", new String[] { "id", "descricao",
				"codigo", "preco" }, null, null, null, null, null);

		/* Colunas a Serem exibidas */
		String[] columns = new String[] { "descricao", "preco" };

		/* Campos a serem selecionados */
		int[] to = new int[] { R.id.descricao, R.id.preco };
		
		/*Criar o adaptador usando o cursor apontando para os dados desejados  as informações LAYOUT */
		SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,R.layout.lista_produto_item, c, columns, to);

		/*Define o Adapter*/
		lista.setAdapter(mAdapter);

		c.close();

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		case R.id.action_adicionar:
			Intent i = new Intent(getBaseContext(), DetailActivity.class);
	    	i.putExtra("id_produto", 0);
	    	startActivity(i);
			return true;
		case R.id.action_sobre:
			Intent s = new Intent(getBaseContext(), SobreActivity.class);
	    	startActivity(s);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}
}
