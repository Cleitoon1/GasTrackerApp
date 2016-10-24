package com.company.alves.gastracker.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.company.alves.gastracker.DataModel.DataModel;
import com.company.alves.gastracker.DataSource.DataSource;
import com.company.alves.gastracker.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cleiton on 05/09/2016.
 */
public class UserDAO {
    DataSource ds;
    ContentValues values;

    public UserDAO(Context context) {
        ds = new DataSource(context);
    }

    //Cria um novo usuário ou se passar o id do registro e a flag atualizar true atualiza o mesmo
    public boolean addNEditUser(User usr) {
        values = new ContentValues();
        values.put("id", usr.getName());
        values.put("name", usr.getName());
        values.put("car", usr.getCar());
        values.put("car_year", usr.getCarYear());
        values.put("avg_consumption", usr.getAvgConsumption());
        try {
            ds.persist(values, DataModel.getTbUser());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Método para retornar o usuário criado.
    public User getUserById(int idUser) {
        Cursor cursor = ds.find(DataModel.getTbUser(), null, "id = " + idUser, null, null, null, null, null);
        User aux = new User();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            aux.setId(cursor.getInt(cursor.getColumnIndex("id")));
            aux.setName(cursor.getString(cursor.getColumnIndex("name")));
            aux.setCar(cursor.getString(cursor.getColumnIndex("car")));
            aux.setCarYear(cursor.getInt(cursor.getColumnIndex("car_year")));
            aux.setAvgConsumption(cursor.getDouble(cursor.getColumnIndex("avg_consumption")));
        }
        return aux;
    }

    public int countUsers(){
        Cursor cursor = ds.find(DataModel.getTbUser(), null, null, null, null, null, null, null);
        return cursor.getCount();
    }
}