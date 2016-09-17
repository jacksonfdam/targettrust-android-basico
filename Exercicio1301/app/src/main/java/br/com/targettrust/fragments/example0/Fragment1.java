package br.com.targettrust.fragments.example0;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.suelengc.devfest.R;

public class Fragment1 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment1, null);
		TextView text = (TextView) view.findViewById(R.id.text1);
		text.setText("Fragment 1");
		return view;
	}

}
