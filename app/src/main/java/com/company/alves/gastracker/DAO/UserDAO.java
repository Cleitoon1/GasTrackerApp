package com.company.alves.gastracker.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.company.alves.gastracker.DataModel.DataModel;
import com.company.alves.gastracker.DataSource.DataSource;
import com.company.alves.gastracker.Model.User;
import com.company.alves.gastracker.Model.Year;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Cleiton on 05/09/2016.
 */
public class UserDAO {
    DataSource ds;
    ContentValues values;
    YearDAO yearDAO;
    private static UserDAO instance;

    public static UserDAO getInstance(Context context){
        if(instance == null){
            instance = new UserDAO(context);
        }
        return instance;
    }

    private UserDAO(Context context) {
        ds = DataSource.getInstance(context);
        yearDAO = YearDAO.getInstance(context);
    }

    //Cria um novo usuário ou se passar o id do registro e a flag atualizar true atualiza o mesmo
    public boolean addNEditUser(User usr) {
        values = new ContentValues();
        values.put("id", usr.getId());
        values.put("name", usr.getName());
        values.put("car", usr.getCar());
        values.put("car_year", usr.getCarYear());
        values.put("avg_consumption", usr.getAvgConsumption());
        try {
            ds.persist(values, DataModel.getTbUser());
            int year = Calendar.getInstance().get(Calendar.YEAR);
            if(yearDAO.getYearByNumber(year).getYear() != year){
                Year y = new Year();
                y.setYear(year);
                yearDAO.addNEditYear(y);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Método para retornar o usuário criado.
    public User getUser() {
        Cursor cursor = ds.find(DataModel.getTbUser(), null, null, null, null, null, null, null);
        User aux = new User();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Log.d("Id", "Id: "+ cursor.getString(cursor.getColumnIndex("id")));
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
        int retorno = cursor.getCount();
        return retorno;
    }
}