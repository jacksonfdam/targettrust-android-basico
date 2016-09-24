/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */


package br.com.targettrust.ttpetshop.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.targettrust.ttpetshop.Pet;

/**
 * http://developer.android.com/reference/android/database/sqlite/package-summary.html
 * http://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "ttpetshop";

    private static final String TABLE_PET = "pets";

    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_ARQUIVO = "arquivo";
    private static final String KEY_FOTO = "foto";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String KEY_CRIADO_EM = "criado_em";

    private static final String CREATE_TABLE_PET = "CREATE TABLE "
            + TABLE_PET + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NOME + " TEXT,"
            + KEY_ARQUIVO + " TEXT,"
            + KEY_FOTO + " TEXT,"
            + KEY_DESCRICAO + " TEXT,"
            + KEY_CRIADO_EM + " DATETIME" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PET);
        onCreate(db);
    }

    // ------------------------ "Pets" table methods ----------------//

    public long createPet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, pet.getNome());
        values.put(KEY_ARQUIVO, pet.getArquivo());
        values.put(KEY_FOTO, pet.getFoto());
        values.put(KEY_DESCRICAO, pet.getDescricao());
        values.put(KEY_CRIADO_EM, getDateTime());

        long pet_id = db.insert(TABLE_PET, null, values);

        return pet_id;
    }

    public Pet getPet(long pet_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PET + " WHERE " + KEY_ID + " = " + pet_id;
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Pet pet = new Pet();
        pet.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        pet.setNome((c.getString(c.getColumnIndex(KEY_NOME))));
        pet.setArquivo((c.getString(c.getColumnIndex(KEY_ARQUIVO))));
        pet.setCriadoEm(c.getString(c.getColumnIndex(KEY_CRIADO_EM)));

        return pet;
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<Pet>();
        String selectQuery = "SELECT  * FROM " + TABLE_PET;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Pet pet = new Pet();
                pet.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pet.setNome((c.getString(c.getColumnIndex(KEY_NOME))));
                pet.setArquivo((c.getString(c.getColumnIndex(KEY_ARQUIVO))));
                pet.setCriadoEm(c.getString(c.getColumnIndex(KEY_CRIADO_EM)));
                pets.add(pet);
            } while (c.moveToNext());
        }

        return pets;
    }
    public List<Pet> getPetsByName(String nome) {
        List<Pet> pets = new ArrayList<Pet>();
        String selectQuery = "SELECT  * FROM " + TABLE_PET +
                " WHERE nome LIKE '%" + nome + "%' ";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Pet pet = new Pet();
                pet.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pet.setNome((c.getString(c.getColumnIndex(KEY_NOME))));
                pet.setArquivo((c.getString(c.getColumnIndex(KEY_ARQUIVO))));
                pet.setCriadoEm(c.getString(c.getColumnIndex(KEY_CRIADO_EM)));
                pets.add(pet);
            } while (c.moveToNext());
        }

        return pets;
    }

    public int getPetCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PET;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int updatePet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOME, pet.getNome());
        values.put(KEY_ARQUIVO, pet.getArquivo());
        values.put(KEY_FOTO, pet.getFoto());
        values.put(KEY_DESCRICAO, pet.getDescricao());
        values.put(KEY_CRIADO_EM, getDateTime());

        return db.update(TABLE_PET, values, KEY_ID + " = ?", new String[] { String.valueOf(pet.getId()) });
    }

    public void deletePet(long pet_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PET, KEY_ID + " = ?", new String[] { String.valueOf(pet_id) });
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}