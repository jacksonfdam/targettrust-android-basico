package br.exemplospinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	private String[] carrosNome = new String[]{"Bugatti Veyron", "Ferrari Laferrari", "Lamborghini Veneno",
												"Maserati Gran Turismo", "Mclaren", "Pagani Zonda", "Porsche 911"};
	
	private int[] carrosImgs = {R.drawable.bugatti_veyron, R.drawable.ferrari_laferrari, R.drawable.lamborghini_veneno,
								R.drawable.maserati_gran_turismo, R.drawable.mclaren, R.drawable.pagani_zonda,
								R.drawable.porsche_911};
	private Spinner sp;
	private ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.carros, android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		iv = (ImageView) findViewById(R.id.imageView1);
		
		sp = new Spinner(this); //(Spinner) findViewById(R.id.spinner1);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		sp.setLayoutParams(lp);
		sp.setAdapter(adapter);
		//sp.setAdapter(new AdapterCarro(this, carrosNome));
		
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				iv.setImageResource(carrosImgs[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);
		ll.addView(sp, 1);
	}
	
	public void showElemento(View view){
		String nome = (String) sp.getSelectedItem();
		long id = sp.getSelectedItemId();
		int posicao = sp.getSelectedItemPosition();
		
		Toast.makeText(this, "Carro: "+nome+" -> Id: "+id+" -> Posição: "+posicao, Toast.LENGTH_SHORT).show();
	}
}
