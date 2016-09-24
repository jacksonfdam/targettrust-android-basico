package br.com.targettrust.ttpetshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    Toast t;
    ListView lv;

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);
        TextView status = (TextView)findViewById(R.id.status);

        if(isConnected()){
            status.setBackgroundColor(0xFF00CC00);
            status.setText("Conectado");
        }
        else{
            status.setText("Sem Conexao");
        }


        List<Map<String, String>> list = new ArrayList< Map <String, String>>();

        Map<String, String> map = new HashMap<String, String>();map.put("Nome", "Beagle" );map.put("arquivo", "beagle");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Border Collie" );map.put("arquivo", "border_collie");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Boxer" );map.put("arquivo", "boxer");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Buldogue Frances" );map.put("arquivo", "buldogue_frances");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Buldogue Ingles" );map.put("arquivo", "buldogue_ingles");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Chow chow" );map.put("arquivo", "chow-chow");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Cocker Spaniel Ingles" );map.put("arquivo", "cocker_spaniel_ingles");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Dachshund" );map.put("arquivo", "dachshund");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Gato Exotico" );map.put("arquivo", "gato_exotico");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Gato Himalaio" );map.put("arquivo", "gato_himalaio");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Gato Persa" );map.put("arquivo", "gato_persa");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Golden Retrivier" );map.put("arquivo", "golden_retrivier");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Labrador" );map.put("arquivo", "labrador");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Lhasa Apso" );map.put("arquivo", "lhasa_apso");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Maltes" );map.put("arquivo", "maltes");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Poodle" );map.put("arquivo", "poodle");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Pug" );map.put("arquivo", "pug");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Schnauzer" );map.put("arquivo", "schnauzer");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Scottish Terrier" );map.put("arquivo", "scottish_terrier");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Shar Pei" );map.put("arquivo", "shar_pei");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Shih Tzu" );map.put("arquivo", "shih_tzu");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "West Highland White Terrier" );map.put("arquivo", "west_highland_white_terrier");list.add(map);
        map = new HashMap<String, String>();map.put("Nome", "Yorkshire" );map.put("arquivo", "yorkshire");list.add(map);

        PetAdapter adapter = new PetAdapter(getBaseContext(), list);

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

    static class ViewHolder {
        TextView nome;
        ImageView foto;
        int position;
    }

    public class PetAdapter extends BaseAdapter {
        List<Map<String, String>> pets;
        Context ctx;
        AssetManager manager = getAssets();
        private String TAG = PetAdapter.class.getSimpleName();
        public PetAdapter(Context ctx, List<Map<String, String>> pets) {
            super();
            this.pets = pets;
            this.ctx = ctx;
            Log.v(TAG,"Construtor");
        }
        @Override
        public int getCount() {
            Log.v(TAG,"getCount : " +  pets.size() );
            return pets.size();
        }
        @Override
        public Map<String, String> getItem(int position) {
            Log.v(TAG,"getItem : " + position);
            return pets.get(position);
        }
        @Override
        public long getItemId(int position) {
            Log.v(TAG,"getItemId : " + position);
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.v(TAG,"getView : " + position);
            View rowView = convertView;
            ViewHolder viewHolder;
            // reusa views
            if (rowView == null) {
                Log.v(TAG,"getView ViewHolder: " + position);
                LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.linha_pet_list_item, null);

                // configura view holder
                viewHolder = new ViewHolder();
                viewHolder.nome = (TextView) rowView.findViewById(R.id.textView);
                viewHolder.foto = (ImageView) rowView.findViewById(R.id.imageView);
                rowView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // acessar o dado da posição
            Map<String, String> pet = getItem(position);

            // fill data
            viewHolder.nome.setText( pet.get("nome") );

            try {
                InputStream ims = manager.open( pet.get("arquivo") + ".jpg");
                Drawable d = Drawable.createFromStream(ims, null);
                viewHolder.foto.setImageDrawable(d);
            } catch(IOException ex) {
                Log.e("PetAdapter", ex.getLocalizedMessage());
            }

            // retorna o layout preenchido
            return rowView;
        }

    }
}