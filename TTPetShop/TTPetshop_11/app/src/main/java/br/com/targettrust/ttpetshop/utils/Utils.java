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
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.targettrust.ttpetshop.R;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();
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

    public static void logHeap(Class clazz) {
        Double allocated = new Double(Debug.getNativeHeapAllocatedSize())/new Double((1048576));
        Double available = new Double(Debug.getNativeHeapSize()/1048576.0);
        Double free = new Double(Debug.getNativeHeapFreeSize()/1048576.0);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        Log.d(clazz.getName(), "debug.heap native: allocated " + df.format(allocated) + "MB of " + df.format(available) + "MB (" + df.format(free) + "MB free) in [" + clazz.getName().replaceAll("com.myapp.android.","") + "]");
        Log.d(clazz.getName(), "debug.memory: allocated: " + df.format(new Double(Runtime.getRuntime().totalMemory()/1048576)) + "MB of " + df.format(new Double(Runtime.getRuntime().maxMemory()/1048576))+ "MB (" + df.format(new Double(Runtime.getRuntime().freeMemory()/1048576)) +"MB free)");
        System.gc();
    }

    public static long calculateDays(Date dateEarly, Date dateLater) {
        return (dateLater.getTime() - dateEarly.getTime()) / (24 * 60 * 60 * 1000);
    }

    /** Using Calendar - THE CORRECT WAY **/
    public static long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    public static boolean checkConn(Context ctx) {
        ConnectivityManager conMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING) {
            return true;

        } else if (conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
            return false;

        }

        return false;
    }

    public static boolean isTabletDevice(Context ctx) {
        if (android.os.Build.VERSION.SDK_INT >= 11) { // honeycomb
// test screen size, use reflection because isLayoutSizeAtLeast is
// only available since 11
            Configuration con = ctx.getResources().getConfiguration();
            try {
                Method mIsLayoutSizeAtLeast = con.getClass().getMethod(
                        "isLayoutSizeAtLeast", int.class);
                boolean r = (Boolean) mIsLayoutSizeAtLeast.invoke(con,
                        0x00000004); // Configuration.SCREENLAYOUT_SIZE_XLARGE
                return r;
            } catch (Exception x) {
                x.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
// Retrieving the external storage state
        String state = Environment.getExternalStorageState();

// Check if available
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageWritable() {
// Retrieving the external storage state
        String state = Environment.getExternalStorageState();

// Check if writable
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    public static boolean createDirIfNotExists(String path) {
        boolean ret = true;
        String extStorageDirectory = Environment.getExternalStorageDirectory()
                .toString() + "/" + path;
        Log.v(TAG, "extStorageDirectory" +  extStorageDirectory);
        File file = new File(extStorageDirectory);
        if (!file.exists()) {
            if (!file.mkdir()) {
                Log.v(TAG, "createDirIfNotExists " + " Problem creating Image folder : "
                        + extStorageDirectory);
                ret = false;
            }
        }
        return ret;
    }

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (;;) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    public static JSONObject getLocationInfo(String address) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            address = address.replaceAll("CEP: .*?", "");
            address = address.replaceAll(" ", "%20");
            String url = "http://maps.google.com/maps/api/geocode/json?address="
                    + address + "&sensor=false";
// log(LOG_TAG, "getLocationInfo", url);
            HttpPost httppost = new HttpPost(url);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response;
            stringBuilder = new StringBuilder();
            response = client.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static String getLatLong(JSONObject jsonObject) {
        Double longitude, latitude;
        try {

            longitude = ((JSONArray) jsonObject.get("results"))
                    .getJSONObject(0).getJSONObject("geometry")
                    .getJSONObject("location").getDouble("lat");
            latitude = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lng");

        } catch (JSONException e) {
            return "";

        }

        return longitude + "," + latitude;
    }

    public static boolean IsNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Toast.makeText(context, "None Available", Toast.LENGTH_SHORT)
                    .show();
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            for (NetworkInfo inf : info) {
                if (inf.getTypeName().contains("WIFI"))
                    if (inf.isConnected())
                        return true;
            }
        }
        return false;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void alertDialog(final Context context, final int mensagem) {
        try {
            AlertDialog dialog = new AlertDialog.Builder(context).setTitle(
                    context.getString(R.string.app_name)).setMessage(mensagem).create();
            dialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
            dialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
    public static void alertDialog(final Context context, final String mensagem) {
        try {
            AlertDialog dialog = new AlertDialog.Builder(context).setTitle(
                    context.getString(R.string.app_name)).setMessage(mensagem)
                    .create();
            dialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
            dialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
    // Retorna se é Android 3.x "honeycomb" ou superior (API Level 11)
    public static boolean isAndroid_3() {
        int apiLevel = Build.VERSION.SDK_INT;
        if (apiLevel >= 11) {
            return true;
        }
        return false;
    }
    // Retorna se a tela é large ou xlarge
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
    // Retona se é um tablet com Android 3.x
    public static boolean isAndroid_3_Tablet(Context context) {
        return isAndroid_3() && isTablet(context);
    }
    // Fecha o teclado virtual se aberto (view com foque)
    public static boolean closeVirtualKeyboard(Context context, View view) {
// Fecha o teclado virtual
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            boolean b = imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return b;
        }
        return false;
    }

    public static void appendLog(String text) {
        File logFile = new File("sdcard/arquivo.xml");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
// BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile,
                    true));
            buf.append(text);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
