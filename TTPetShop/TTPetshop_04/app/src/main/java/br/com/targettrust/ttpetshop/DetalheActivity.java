package br.com.targettrust.ttpetshop;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;


public class DetalheActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        String extra_nome = getIntent().getStringExtra("nome");
        String extra_arquivo = getIntent().getStringExtra("arquivo");


        ImageView foto = (ImageView) findViewById(R.id.foto);
        TextView  nome = (TextView) findViewById(R.id.nome);
        TextView  descricao = (TextView) findViewById(R.id.descricao);

        AssetManager manager = getAssets();
        /*
        * String[] files = assetManager.list("");  // files from 'assets' directory
        * String[] files = assetManager.list("Files"); // files from 'assets/Files'
        */

        nome.setText(extra_nome);

        try {
            String[] files = manager.list("");

            for(int i=0; i<files.length; i++){
                Log.v("MainActivity", "\n Arquivo :" + i + " Nome => " + files[i]);
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        InputStream input;
        try {
            input = manager.open(extra_arquivo + ".txt");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            String text = new String(buffer);
            descricao.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream ims = manager.open(extra_arquivo + ".jpg");
            Drawable d = Drawable.createFromStream(ims, null);
            foto.setImageDrawable(d);
        } catch(IOException ex) {
            return;
        }

    }
}
