package br.exemplorelativelayout;


import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		/*RelativeLayout rl = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		rl.setLayoutParams(lp);
		
		TextView labelUsuario = new TextView(this);
		labelUsuario.setText("Usuário: ");
		labelUsuario.setId(R.id.labelUsuario);
		lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, (int)getResources().getDimension(R.dimen.dez_dp), 0, 0); // L T R B
		labelUsuario.setLayoutParams(lp);
		rl.addView(labelUsuario);
		
		EditText campoUsuario = new EditText(this);
		campoUsuario.setId(R.id.campoUsuario);
		lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.RIGHT_OF, labelUsuario.getId());
		campoUsuario.setLayoutParams(lp);
		rl.addView(campoUsuario);
		
		setContentView(rl);*/
	}

}
