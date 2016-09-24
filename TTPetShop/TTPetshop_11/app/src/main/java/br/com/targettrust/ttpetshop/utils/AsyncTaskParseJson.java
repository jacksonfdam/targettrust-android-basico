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
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

import br.com.targettrust.ttpetshop.Pet;
import br.com.targettrust.ttpetshop.R;
import br.com.targettrust.ttpetshop.activities.DetalheActivity;
import br.com.targettrust.ttpetshop.activities.MainActivity;
import br.com.targettrust.ttpetshop.adapters.PetJSONAdapter;

public class AsyncTaskParseJson extends AsyncTask<Void, Void, String> {
    final String TAG = "AsyncTaskParseJson";
    Activity activity;
    DatabaseHelper db;
    InputStream inputStream = null;
    String result = "";
    ProgressDialog progressDialog;
    String webservice_url = "https://gist.githubusercontent.com/jacksonfdam/6a3bbc8a104878a5c1b8/raw/d4305882ea989a940d279c69623f694c1ac59135/pets";
    JSONArray dataJsonArr = null;
    Toast t;

    public AsyncTaskParseJson(Activity activity){
        this.activity = activity;
        progressDialog = new ProgressDialog(activity);
        db = new DatabaseHelper(activity);
    }
    @Override
    protected void onPreExecute() {
        Log.v(TAG, webservice_url);
        progressDialog.setMessage("Downloading ...");
        progressDialog.show();
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
                AsyncTaskParseJson.this.cancel(true);
            }
        });
    }

    @Override
    protected String doInBackground(Void... params) {
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(webservice_url));
            inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null)
                result = Utils.convertInputStreamToString(inputStream);
            else
                result = "Ocorreu um erro";
        } catch (Exception e) {
            AsyncTaskParseJson.this.cancel(true);
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }


    @Override
    protected void onPostExecute(String strFromDoInBg) {
        Log.v(TAG, "strFromDoInBg : " + strFromDoInBg);
        JSONObject pet = null;
        try {
            pet = new JSONObject(strFromDoInBg);
            JSONArray jArray = pet.getJSONArray("pets");
            Log.v(TAG, "jArray : " + jArray.length());
            for(int i=0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                List<Pet> petsExistentes = db.getPetsByName(jObject.getString("nome"));
                if(petsExistentes.size() == 0){
                    Pet petFromJson = new Pet(jObject.getString("nome"),jObject.getString("arquivo"));
                    long petFromJson_id = db.createPet(petFromJson);
                    Log.v(TAG, petFromJson_id + " - " + petFromJson.getNome() + " - " +petFromJson.getArquivo());
                }
            }
            Log.v(TAG, "Pet Count: " + db.getAllPets().size());
            db.closeDB();
            ListView lv = (ListView)((MainActivity)activity).findViewById(R.id.listView);

            PetJSONAdapter adapter = new PetJSONAdapter(activity, jArray);

            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {
                    JSONObject pet = null;
                    pet = (JSONObject) adapter.getItemAtPosition(position);
                    try {
                        t = Toast.makeText(activity, "Selecionado: " + pet.getString("nome"), Toast.LENGTH_SHORT);
                        t.show();
                        Intent detalhe = new Intent(activity,DetalheActivity.class);
                        detalhe.putExtra("nome", pet.getString("nome") );
                        detalhe.putExtra("arquivo", pet.getString("arquivo") );
                        activity.startActivity(detalhe);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            if (progressDialog.isShowing())
                progressDialog.dismiss();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}