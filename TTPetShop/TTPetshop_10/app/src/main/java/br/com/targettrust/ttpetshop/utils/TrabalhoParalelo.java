/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */

package br.com.targettrust.ttpetshop.utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TrabalhoParalelo extends AsyncTask<String, Void, Bitmap> {

    String TAG = "TrabalhoParalelo";
    ImageView iv;

    public TrabalhoParalelo(ImageView iv){
        this.iv = iv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap img = null;
        try{

            URL url = new URL(params[0]);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream input = conexao.getInputStream();
            img = BitmapFactory.decodeStream(input);

            String linha;
            String resultado = "";
            URL url1 = new URL("https://gist.githubusercontent.com/jacksonfdam/7e941a6f2330c6b207ee/raw/8bd854ce274e9a4a13c71400292c439828732592/mensagem.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(url1.openStream()));
            while ((linha = in.readLine()) != null) {
                resultado += linha;
            }
            in.close();
            Log.v(TAG, resultado);

        }
        catch(IOException e){}

        return img;
    }
    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if(result != null)
            this.iv.setImageBitmap(result);
    }

}