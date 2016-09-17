package br.com.targettrust.cadastro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

	
	public DBHandler(Context context) {
		super(context, "db_teste.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("DBHandler", "criando banco...");

		StringBuilder strb = new StringBuilder();
        
        strb.append(" CREATE TABLE IF NOT EXISTS PRODUTOS (");
        strb.append(" id integer primary key autoincrement,");
        strb.append(" descricao varchar(30),");
        strb.append(" codigo varchar(100), ");
        strb.append(" preco double )");
        
        db.execSQL(strb.toString());
        
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion) {
		Log.d("DBHandler", "upgrade no banco... older: " + olderVersion + " -> new: " + newVersion);
		
		db.execSQL("ALTER TABLE PRODUTOS ADD COLUMN CAMINHO_FOTO VARCHAR(30)");

	}

}
