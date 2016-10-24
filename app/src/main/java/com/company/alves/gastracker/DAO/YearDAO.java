package com.company.alves.gastracker.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.company.alves.gastracker.DataModel.DataModel;
import com.company.alves.gastracker.DataSource.DataSource;
import com.company.alves.gastracker.Model.Year;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cleiton on 08/09/2016.
 */
public class YearDAO {
    DataSource ds;
    ContentValues values;

    public YearDAO(Context context) {
        ds = new DataSource(context);
    }

    //Cria um novo ano ou se passar o id do registro e a flag atualizar true atualiza o mesmo
    //criar o ano de acordo com o ano corrente, verificando se não existe antes.
    public boolean addNEditYear(Year year) {
        values = new ContentValues();
        values.put("id", year.getId());
        values.put("year", year.getYear());
        try {
            ds.persist(values, DataModel.getTbYear());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Year> getYears(){
        List<Year> retorno = new ArrayList<Year>();
        Cursor cursor = ds.find(DataModel.getTbYear(), null, null, null, null, null, null, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            for(int i = 0; i < cursor.getCount(); i++) {
                Year aux = new Year();
                aux.setId(cursor.getInt(cursor.getColumnIndex("id")));
                aux.setYear(cursor.getInt(cursor.getColumnIndex("year")));
                retorno.add(aux);
                cursor.moveToNext();
            }
        }
        return retorno;
    }

    //Busca ano pelo número
    public Year getYearByNumber(int ano){
        Cursor cursor = ds.find(DataModel.getTbYear(), null, "year = " + ano, null, null, null, null, null);
        Year retorno = new Year();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            retorno.setId(cursor.getInt(cursor.getColumnIndex("id")));
            retorno.setYear(cursor.getInt(cursor.getColumnIndex("year")));
        }
        return retorno;
    }

    public Boolean deleteYear(int idYear){
        try{
            ds.delete(DataModel.getTbYear(), "id = " + idYear, null);
            return true;
        } catch (Exception e){
            return false;
        }
    }


}


