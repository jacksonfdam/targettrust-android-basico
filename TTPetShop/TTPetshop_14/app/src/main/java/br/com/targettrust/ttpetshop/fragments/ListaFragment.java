/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */

package br.com.targettrust.ttpetshop.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import br.com.targettrust.ttpetshop.Pet;
import br.com.targettrust.ttpetshop.R;
import br.com.targettrust.ttpetshop.TtpetShop;
import br.com.targettrust.ttpetshop.activities.DetalheActivity;
import br.com.targettrust.ttpetshop.adapters.PetAdapter;
import br.com.targettrust.ttpetshop.utils.AsyncTaskParseJson;
import br.com.targettrust.ttpetshop.utils.DatabaseHelper;
import br.com.targettrust.ttpetshop.utils.DebugFragment;
import br.com.targettrust.ttpetshop.utils.TrabalhoParalelo;
import br.com.targettrust.ttpetshop.utils.Utils;


public class ListaFragment extends DebugFragment {

    ListView lv;
    Toast t;
    ImageView iv;
    TextView status;
    Utils util;
    View v;
    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_lista, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        lv = (ListView) v.findViewById(R.id.listView);
        status = (TextView) v.findViewById(R.id.status);
        iv = (ImageView) v.findViewById(R.id.logo);
        util = new Utils(this.getActivity());
        carregar();
    }

    private void carregar(){

        if(util.isConnected()){
            status.setBackgroundColor(0xFF00CC00);
            status.setText("Conectado");
            new TrabalhoParalelo(iv).execute("http://www.targettrust.com.br/img/header-logo_v2.png");

            SharedPreferences prefs = getActivity().getPreferences(0);
            long lastUpdateTime =  prefs.getLong("lastUpdateTime", 0);

            if ((lastUpdateTime + (24 * 60 * 60 * 1000)) < System.currentTimeMillis()) {

                lastUpdateTime = System.currentTimeMillis();
                SharedPreferences.Editor editor = getActivity().getPreferences(0).edit();
                editor.putLong("lastUpdateTime", lastUpdateTime);
                editor.commit();
                util.checkUpdate.start();
                new AsyncTaskParseJson(getActivity()).execute();
            }

            loadOffLineData();
        }
        else{
            status.setText("Sem Conexao");
            loadOffLineData();
        }
    }

    public void loadOffLineData(){
        TtpetShop app = (TtpetShop)getActivity().getApplicationContext();

        DatabaseHelper db = app.db;
        List<Pet> allPets = db.getAllPets();

        PetAdapter adapter = new PetAdapter(getActivity().getBaseContext(), allPets);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {
                Map<String, String> pet = (Map<String, String>) adapter.getItemAtPosition(position);
                t = Toast.makeText(getActivity().getBaseContext(),"Selecionado: " + pet.get("Nome"),Toast.LENGTH_SHORT);
                t.show();
                Intent detalhe = new Intent(getActivity().getBaseContext(),DetalheActivity.class);
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
                t = Toast.makeText(getActivity().getBaseContext(),"Long Click: " + pet.get("Nome"),Toast.LENGTH_SHORT);
                t.show();
                return true;
            }
        });
    }


}
