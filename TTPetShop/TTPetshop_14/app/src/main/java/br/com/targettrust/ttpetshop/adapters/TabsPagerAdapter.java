package br.com.targettrust.ttpetshop.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import br.com.targettrust.ttpetshop.fragments.ContatoFragment;
import br.com.targettrust.ttpetshop.fragments.LocalizacaoFragment;
import br.com.targettrust.ttpetshop.fragments.SobreFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return new ListFragment();
		case 1:
			return new LocalizacaoFragment();
		case 2:
			return new SobreFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
