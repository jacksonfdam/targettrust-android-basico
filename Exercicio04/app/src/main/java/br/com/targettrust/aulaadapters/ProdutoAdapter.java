package br.com.targettrust.aulaadapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProdutoAdapter extends BaseAdapter {

	List<ProdutoVO> produtos;
	Context ctx;
	
	public ProdutoAdapter(Context ctx, List<ProdutoVO> produtos) {
		super();
		this.produtos = produtos;
		this.ctx = ctx;
	}

	@Override
	public int getCount() {
		return produtos.size();
	}

	@Override
	public ProdutoVO getItem(int position) {
		return produtos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// acessar o dado da posição
		ProdutoVO produto = getItem(position);
		
		
		// inflar o layout
		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.linha_produto, null);
		
		// preencher o layout
		TextView tvNome = (TextView) v.findViewById(R.id.linha_produto_tvProduto);
		tvNome.setText( produto.getNome() );
		
		TextView tvPreco = (TextView) v.findViewById(R.id.linha_produto_tvPreco);
		tvPreco.setText( String.valueOf( produto.getPreco() ) );
		
		// retorna o layout preenchido
		return v;
	}

}
