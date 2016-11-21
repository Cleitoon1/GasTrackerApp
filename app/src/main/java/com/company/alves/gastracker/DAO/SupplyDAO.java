package com.company.alves.gastracker.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.company.alves.gastracker.DataModel.DataModel;
import com.company.alves.gastracker.DataSource.DataSource;
import com.company.alves.gastracker.Model.Supply;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cleiton on 17/09/2016.
 */
public class SupplyDAO {
    DataSource ds;
    ContentValues values;
    private static SupplyDAO instance;

    public static SupplyDAO getInstance(Context context){
        if(instance == null){
            instance = new SupplyDAO(context);
        }
        return instance;
    }
    private SupplyDAO(Context context){
        ds = DataSource.getInstance(context);
    }

    //Cria um novo abastecimento ou se passar o id do registro e a flag atualizar true atualiza o mesmo
    public boolean addNeditSupply(Supply sup){
        values = new ContentValues();
        values.put("id", sup.getId());
        values.put("value", sup.getValue());
        values.put("liters", sup.getLiters());
        values.put("gas_station", sup.getGasStation());
        values.put("month_id ", sup.getIdMonth());
        try {
            ds.persist(values, DataModel.getTbMonth());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    //Retora os dados do abastecimento passando o seu ID
    public Supply getSupply(int idSup){
        Cursor cursor = ds.find(DataModel.getTbSupply(),null, "id = " + idSup, null, null, null, null, null);
        Supply retorno = new Supply();
        String str = "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            str = cursor.getString(cursor.getColumnIndex("date"));
            Date date = new Date();
            try {
                date = format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            retorno.setId(cursor.getInt(cursor.getColumnIndex("id")));
            retorno.setValue(cursor.getDouble(cursor.getColumnIndex("value")));
            retorno.setLiters(cursor.getDouble(cursor.getColumnIndex("liters")));
            retorno.setDate(date);
            retorno.setGasStation(cursor.getString(cursor.getColumnIndex("gas_station")));
            retorno.setIdMonth(cursor.getInt(cursor.getColumnIndex("month_id")));
        }
        cursor.close();
        return retorno;
    }

    //Buscar os abastecimentos passando o id do mes.
    public List<Supply> getSupplyByMonth(int idMonth){
        List<Supply> lst = new ArrayList<Supply>();
        Cursor cursor = ds.find(DataModel.getTbSupply(),null, "month_id = " + idMonth, null, null, null, null, null);
        String str = "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        if(cursor.getCount() > 0){
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToFirst();
                str= "";
                Supply aux = new Supply();
                str = cursor.getString(cursor.getColumnIndex("date"));
                Date date = new Date();
                try {
                    date = format.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                aux.setId(cursor.getInt(cursor.getColumnIndex("id")));
                aux.setValue(cursor.getDouble(cursor.getColumnIndex("value")));
                aux.setLiters(cursor.getDouble(cursor.getColumnIndex("liters")));
                //aux.setDate();
                aux.setGasStation(cursor.getString(cursor.getColumnIndex("gas_station")));
                aux.setIdMonth(cursor.getInt(cursor.getColumnIndex("month_id")));
                cursor.moveToNext();
                lst.add(aux);
            }
        }
        cursor.close();
        return lst;
    }

    //Deleta o abastecimento se o usuário preferir
    public Boolean deleteSupply(int idSup){
        try{
            ds.delete(DataModel.getTbSupply(), "id = " + idSup, null);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Boolean deleteSupplysByMonth(int idMonth){
        try{
            ds.delete(DataModel.getTbSupply(), "month_id = " + idMonth, null);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
