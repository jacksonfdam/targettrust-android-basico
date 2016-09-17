package br.exemplobaseadapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CarroAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Carro> lista;
	
	public CarroAdapter(Context context, ArrayList<Carro> lista){
		this.context = context;
		this.lista = lista;
	}
	
	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Carro carro = lista.get(position);
		View layout;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.carros, null);
		}
		else{
			layout = convertView;
		}
		
		
		TextView modelo = (TextView) layout.findViewById(R.id.t1);
		modelo.setText(carro.getModelo());
		
		TextView marca = (TextView) layout.findViewById(R.id.t2);
		marca.setText(carro.getMarca());
		
		ImageView iv = (ImageView) layout.findViewById(R.id.iv);
		iv.setImageResource(carro.getCarroImagem(position));
		
		if(position % 2 == 0){
			layout.setBackgroundColor(Color.YELLOW);
		}
		
		return layout;
	}

}
