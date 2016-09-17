package br.com.targettrust.main;

import java.util.List;

import br.com.suelengc.devfest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterItemExample extends BaseAdapter {

	    private LayoutInflater mInflater;
	    private List<ItemExample> itens;

	    public AdapterItemExample(Context context, List<ItemExample> listExamples) {
	        //Itens que preencheram o listview
	        this.itens = listExamples;
	        
	        //responsavel por pegar o Layout do item.
	        mInflater = LayoutInflater.from(context);
	    }

	    /**
	     * Retorna a quantidade de itens
	     *
	     * @return
	     */
	    public int getCount() {
	        return itens.size();
	    }

	    /**
	     * Retorna o item de acordo com a posicao dele na tela.
	     *
	     * @param position
	     * @return
	     */
	    public ItemExample getItem(int position) {
	        return itens.get(position);
	    }

	    /**
	     * Sem implementação
	     *
	     * @param position
	     * @return
	     */
	    public long getItemId(int position) {
	        return position;
	    }

	    public View getView(int position, View view, ViewGroup parent) {
	        //Pega o item de acordo com a posção.
	        ItemExample item = itens.get(position);
	        
	        //infla o layout para podermos preencher os dados
	        view = mInflater.inflate(R.layout.item_example, null);

	        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
	        //ao item e definimos as informações.
	        ((TextView) view.findViewById(R.id_item_example.name)).setText(item.getName());
	        ((TextView) view.findViewById(R.id_item_example.description)).setText(item.getDescription());

	        return view;
	    }
}
