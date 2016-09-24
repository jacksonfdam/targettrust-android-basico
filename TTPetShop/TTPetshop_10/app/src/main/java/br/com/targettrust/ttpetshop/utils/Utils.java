/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */

package br.com.targettrust.ttpetshop.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.Toast;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import br.com.targettrust.ttpetshop.R;

public class Utils {
    Context ctx;
    Handler mHandler;

    public Utils(Context ctx){
        this.ctx = ctx;
        mHandler = new Handler();
    }
    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager)ctx.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public Runnable showUpdate = new Runnable(){
        public void run(){
            Vibrator v = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);

            new AlertDialog.Builder(ctx)
                    .setIcon(R.drawable.ic_launcher)
                    .setTitle("Atualização Disponivel")
                    .setMessage("Existe uma atualização disponivel, deseja verificar?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(ctx, "Atualizando sistema", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(ctx, "Atualização cancelada",Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();
        }
    };
    public Thread checkUpdate = new Thread() {
        public void run() {
            try {
                URL updateURL = new URL("https://gist.githubusercontent.com/anonymous/3906da947187879d882d/raw/d8263ee9860594d2806b0dfd1bfd17528b0ba2a4/update.txt");
                URLConnection conn = updateURL.openConnection();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayBuffer baf = new ByteArrayBuffer(50);

                int current = 0;
                while((current = bis.read()) != -1){
                    baf.append((byte)current);
                }
                final String s = new String(baf.toByteArray());

                int curVersion = ctx.getPackageManager().getPackageInfo("br.com.targettrust.ttpetshop", 0).versionCode;
                int newVersion = Integer.valueOf(s);

                if (newVersion > curVersion) {
                    mHandler.post(showUpdate);
                }
            } catch (Exception e) {
            }
        }
    };


}
