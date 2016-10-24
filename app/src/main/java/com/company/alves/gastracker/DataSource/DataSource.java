package com.company.alves.gastracker.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.company.alves.gastracker.DataModel.DataModel;

/**
 * Created by Cleiton on 05/09/2016.
 */
public class DataSource extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public DataSource(Context context) {
        super(context, DataModel.getDbName(), null, 1);
        db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataModel.createTableUser());
        db.execSQL(DataModel.createTableYear());
        db.execSQL(DataModel.createTableMonth());
        db.execSQL(DataModel.createTableSupply());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    //Se passar sem ID, ele cria um novo registro, se tiver id ele da update no registro
    public void persist(ContentValues values, String tabela){
        if(values.containsKey("id") && values.getAsInteger("id") != null && values.getAsInteger("id") != 0){
            Integer id = values.getAsInteger("id");
            db.update(tabela, values, "id = " + id, null);
        }else{
            db.insert(tabela, null, values);
        }
    }

    //método para dar select no banco.
    public Cursor find(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit){
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    //deletar o registo de uma tabela
    public void delete(String table, String where, String[] whereClauses) {
        db.delete(table, where, whereClauses);
    }
}