package br.exemplofragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.layout_frag_2, null);
		
		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText("Fragment 2");
		
		return(view);
	}
}
