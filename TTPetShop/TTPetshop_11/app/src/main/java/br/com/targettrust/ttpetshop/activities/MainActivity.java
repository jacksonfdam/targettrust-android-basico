/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */

package br.com.targettrust.ttpetshop.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import br.com.targettrust.ttpetshop.Pet;
import br.com.targettrust.ttpetshop.R;
import br.com.targettrust.ttpetshop.TtpetShop;
import br.com.targettrust.ttpetshop.adapters.PetAdapter;
import br.com.targettrust.ttpetshop.utils.AsyncTaskParseJson;
import br.com.targettrust.ttpetshop.utils.DatabaseHelper;
import br.com.targettrust.ttpetshop.utils.DebugActivity;
import br.com.targettrust.ttpetshop.utils.TrabalhoParalelo;
import br.com.targettrust.ttpetshop.utils.Utils;

public class MainActivity extends DebugActivity {

    ListView lv;
    Toast t;
    ImageView iv;
    TextView status;
    Utils util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        status = (TextView) findViewById(R.id.status);
        iv = (ImageView) findViewById(R.id.logo);
        util = new Utils(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        carregar();
    }
    private void carregar(){

        if(util.isConnected()){
            status.setBackgroundColor(0xFF00CC00);
            status.setText("Conectado");
            new TrabalhoParalelo(iv).execute("http://www.targettrust.com.br/img/header-logo_v2.png");

            SharedPreferences prefs = getPreferences(0);
            long lastUpdateTime =  prefs.getLong("lastUpdateTime", 0);

            if ((lastUpdateTime + (24 * 60 * 60 * 1000)) < System.currentTimeMillis()) {

                lastUpdateTime = System.currentTimeMillis();
                SharedPreferences.Editor editor = getPreferences(0).edit();
                editor.putLong("lastUpdateTime", lastUpdateTime);
                editor.commit();
                util.checkUpdate.start();
            }
            new AsyncTaskParseJson(this).execute();
        }
        else{
            status.setText("Sem Conexao");
            loadOffLineData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("Buscar");
                alert.setMessage("Pesquisar por animal");

                final EditText input = new EditText(this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        Toast.makeText(getBaseContext(),"Você buscou por " +value ,Toast.LENGTH_LONG).show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
                return true;
            case R.id.action_settings:
                Toast.makeText(getBaseContext(),"Abrir Settings",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadOffLineData(){
        TtpetShop app = (TtpetShop)getApplicationContext();

        DatabaseHelper db = app.db;
        List<Pet> allPets = db.getAllPets();

        PetAdapter adapter = new PetAdapter(getBaseContext(), allPets);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {
                Map<String, String> pet = (Map<String, String>) adapter.getItemAtPosition(position);
                t = Toast.makeText(getBaseContext(),"Selecionado: " + pet.get("Nome"),Toast.LENGTH_SHORT);
                t.show();
                Intent detalhe = new Intent(getBaseContext(),DetalheActivity.class);
                detalhe.putExtra("nome", pet.get("Nome") );
                detalhe.putExtra("arquivo", pet.get("arquivo") );
                startActivity(detalhe);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position,long id) {
                Map<String, String> pet = (Map<String, String>) adapter.getItemAtPosition(position);
                if (t != null) {
                    t.cancel();
                }
                t = Toast.makeText(getBaseContext(),"Long Click: " + pet.get("Nome"),Toast.LENGTH_SHORT);
                t.show();
                return true;
            }
        });
    }






}