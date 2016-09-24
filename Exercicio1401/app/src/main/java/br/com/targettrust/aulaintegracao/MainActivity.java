package br.com.targettrust.aulaintegracao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getProduto(View v) {
    	
    	ServerDelegate.getProduto();
    	
    }
    
    
    public void getProdutos(View v) {
    	
    	ServerDelegate.getProdutos();
    	
    }
    
    
    public void setProduto(View v) {
    	
    	ProdutoVO produto = new ProdutoVO();
    	produto.setDescricao("Produto Jean");
    	produto.setCodigo("12345");
    	produto.setPreco(12.0);
    	
    	ServerDelegate.setProduto(produto);
    	
    	
    	
    }
    
}
