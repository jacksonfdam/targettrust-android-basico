package br.com.targettrust.ttpetshop;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView foto = (ImageView) findViewById(R.id.foto);
        TextView  nome = (TextView) findViewById(R.id.nome);
        TextView  descricao = (TextView) findViewById(R.id.descricao);

        AssetManager manager = getAssets();
        /*
        * String[] files = assetManager.list("");  // files from 'assets' directory
        * String[] files = assetManager.list("Files"); // files from 'assets/Files'
        */

        nome.setText("Beagle");

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
            input = manager.open("beagle.txt");
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
            InputStream ims = manager.open("beagle.jpg");
            Drawable d = Drawable.createFromStream(ims, null);
            foto.setImageDrawable(d);
        } catch(IOException ex) {
            return;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
