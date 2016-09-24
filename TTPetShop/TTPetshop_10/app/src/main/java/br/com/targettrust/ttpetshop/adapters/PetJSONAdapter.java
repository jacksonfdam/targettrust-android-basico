/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */


package br.com.targettrust.ttpetshop.adapters;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import br.com.targettrust.ttpetshop.R;

public class PetJSONAdapter extends BaseAdapter {
    JSONArray pets;
    Context ctx;
    AssetManager manager;
    private String TAG = PetAdapter.class.getSimpleName();
    public PetJSONAdapter(Context ctx, JSONArray pets) {
        super();
        this.pets = pets;
        this.ctx = ctx;
        manager = ctx.getAssets();
        Log.v(TAG, "Construtor");
    }
    @Override
    public int getCount() {
        Log.v(TAG,"getCount : " +  pets.length() );
        return pets.length();
    }
    @Override
    public JSONObject getItem(int position) {
        JSONObject jPet = null;
        Log.v(TAG,"getItem : " + position);

        try {
            jPet = pets.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jPet;
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
        JSONObject pet = getItem(position);

        // fill data
        try {
            viewHolder.nome.setText( pet.getString("nome") );

            try {
                InputStream ims = manager.open( pet.getString("arquivo") + ".jpg");
                Drawable d = Drawable.createFromStream(ims, null);
                viewHolder.foto.setImageDrawable(d);
            } catch(IOException ex) {
                Log.e("PetAdapter", ex.getLocalizedMessage());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // retorna o layout preenchido
        return rowView;
    }
    static class ViewHolder {
        TextView nome;
        ImageView foto;
        int position;
    }

}