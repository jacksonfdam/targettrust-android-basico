package br.com.targettrust.aulacsv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;


/*
 * Baseado em: How To Read And Parse CSV File In Java
 * http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayList<Produto> prdList= new ArrayList<Produto>();
		InputStream is = null;
		AssetManager assetManager = getAssets();
		
        try {
            is = assetManager.open("produtos.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = "";
        StringTokenizer st = null;
        try {

            while ((line = reader.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                Produto prd= new Produto();
                prd.codigo = st.nextToken();
                prd.nome = st.nextToken();
                prd.preco = st.nextToken();

                prdList.add(prd);
                
                Log.v(MainActivity.class.toString(),"Produto: > codigo " +  prd.codigo + " nome " +  prd.nome  + " preco R"+  prd.preco);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

	}
}
