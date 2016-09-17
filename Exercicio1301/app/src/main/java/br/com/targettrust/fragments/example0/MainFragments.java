package br.com.targettrust.fragments.example0;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import br.com.suelengc.devfest.R;

public class MainFragments extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fragments);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuItem m1 = menu.add(0, 0, 0, "Alterar texto frag 2");
		m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		MenuItem m2 = menu.add(0, 1, 0, "Substitui frag 1 p/ frag 3");
		m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		FragmentManager fm = getFragmentManager();
		switch (item.getItemId()) {
		case 0:
			Fragment2 frag2 = (Fragment2) fm.findFragmentById(R.id.frag2);
			frag2.setTexto("Texto atualizado, graças a um click");
			break;
		case 1:
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.replace(R.id.frame1, new Fragment3());
			transaction.commit();
		}

		return true;
	}

}
