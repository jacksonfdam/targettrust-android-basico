package br.com.targettrust.aulafiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView tvPropriedade;
	private EditText etPropriedade;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvPropriedade = (TextView) findViewById(R.id.text_propriedade);
		etPropriedade = (EditText) findViewById(R.id.edit_propriedade);

	}

	public void gravarPropriedade(View v) {

		String valor = etPropriedade.getText().toString();

		SharedPreferences prefs = getSharedPreferences("arquivoProps",
				MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("propriedade_1", valor);

		editor.commit();

	}

	public void lerPropriedade(View v) {

		SharedPreferences prefs = getSharedPreferences("arquivoProps",
				MODE_PRIVATE);
		String valor = prefs.getString("propriedade_1", "DEFAULT!!");

		tvPropriedade.setText(valor);
	}


	public void gravarArquivoInterno(View v) {
		String st = "teste para gravação !!!";
		try {
			FileOutputStream fos = openFileOutput("arquivoInterno.txt",
					MODE_PRIVATE);
			fos.write(st.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void lerArquivoInterno(View v) {
		try {
			FileInputStream fis = openFileInput("arquivoInterno.txt");
			InputStreamReader inputreader = new InputStreamReader(fis);
			BufferedReader buffreader = new BufferedReader(inputreader);
			String line;
			while ((line = buffreader.readLine()) != null) {
				Toast.makeText(getBaseContext(), line, Toast.LENGTH_SHORT)
						.show();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void gravarArquivoExterno(View v) {

		String st = "teste para gravação EXTERNO !!!";

		try {
			File sdDir = Environment.getExternalStorageDirectory();
			FileOutputStream fos = new FileOutputStream(sdDir + "/"
					+ "arquivoExterno.txt");

			fos.write(st.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void lerArquivoExterno(View v) {

		try {
			File sdDir = Environment.getExternalStorageDirectory();
			File arqExt = new File(sdDir, "arquivoExterno.txt");
			FileInputStream fis = new FileInputStream(arqExt);

			InputStreamReader inputreader = new InputStreamReader(fis);
			BufferedReader buffreader = new BufferedReader(inputreader);

			String line;

			while ((line = buffreader.readLine()) != null) {
				Toast.makeText(getBaseContext(), line, Toast.LENGTH_SHORT)
						.show();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	
	}

}
