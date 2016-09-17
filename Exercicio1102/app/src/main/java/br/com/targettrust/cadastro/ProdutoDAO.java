package br.com.targettrust.cadastro;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProdutoDAO {
	
	private SQLiteDatabase db;
	private String nomeTabela = "PRODUTOS";
	private String[] colunas = new String[] { "id", "descricao", "codigo", "preco" };
	
	public ProdutoDAO(Context ctx) {
		db = new DBHandler(ctx).getWritableDatabase();
	}

	public void close() {
		db.close();
	}
	
	public ProdutoVO selectById(int id) {
		
    	Cursor c = db.query(
    			nomeTabela, 
    			colunas, 
    			"id = ?", 
    			new String[]{String.valueOf(id)}, null, null, null);
    	
    	if (c.moveToFirst()) {
    		
    		ProdutoVO produto = new ProdutoVO();
    		produto.setId(c.getInt(c.getColumnIndex("id")));
    		produto.setDescricao(c.getString(c.getColumnIndex("descricao")));
    		produto.setCodigo(c.getString(c.getColumnIndex("codigo")));
    		produto.setPreco(c.getDouble(c.getColumnIndex("preco")));
    		
    		return produto;
    	}

    	return null;
	}

	public List<ProdutoVO> selectAll() {
		
    	Cursor c = db.query(nomeTabela, colunas, null, null, null, null, null);
    	
    	List<ProdutoVO> list = new ArrayList<ProdutoVO>();
    	
    	while (c.moveToNext()) {
    		
    		ProdutoVO produto = new ProdutoVO();
    		produto.setId(c.getInt(c.getColumnIndex("id")));
    		produto.setDescricao(c.getString(c.getColumnIndex("descricao")));
    		produto.setCodigo(c.getString(c.getColumnIndex("codigo")));
    		produto.setPreco(c.getDouble(c.getColumnIndex("preco")));
    		
    		list.add(produto);
    	}

    	return list;
	}

	public boolean insert(ProdutoVO produto) {
		
		ContentValues cv = new ContentValues();
        cv.put("descricao", produto.getDescricao());
        cv.put("codigo", produto.getCodigo());
        cv.put("preco", produto.getPreco());
        
        long rowId = db.insert(nomeTabela, null, cv);
        
        return (rowId > 0);
	}

	public boolean update(ProdutoVO produto) {
		
		ContentValues cv = new ContentValues();
        cv.put("descricao", produto.getDescricao());
        cv.put("codigo", produto.getCodigo());
        cv.put("preco", produto.getPreco());
        
        int result = db.update(nomeTabela, cv, "id = ?", 
        		new String[] { String.valueOf(produto.getId()) });
        
        return (result > 0);
	}
	
	public boolean delete(ProdutoVO produto) {
		
        int result = db.delete(
        		nomeTabela, 
        		"id = ?",
        		new String[] { String.valueOf(produto.getId()) });
        
        return (result > 0);
	}
	

}
