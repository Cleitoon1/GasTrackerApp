package com.company.alves.gastracker.Model;

import java.util.Date;

/**
 * Created by Cleiton on 17/09/2016.
 */
public class Supply {
    private int id;
    private double value;
    private double liters;
    private Date date;
    private String gasStation;
    private int idMonth;

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMonth() {
        return idMonth;
    }

    public void setIdMonth(int idMonth) {
        this.idMonth = idMonth;
    }

    @Override
    public String toString() {
        return "Posto: " + gasStation + " Valor(R$): " +
                value + " Litros: " + liters;
    }
}
