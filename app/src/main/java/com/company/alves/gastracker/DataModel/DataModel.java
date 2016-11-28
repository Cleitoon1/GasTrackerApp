package com.company.alves.gastracker.DataModel;

/**
 * Created by Cleiton on 05/09/2016.
 */
public class DataModel {
    private static final String DB_NAME = "db_gas_tracker.sqlite";
    private static final String TB_USER = "user";
    private static final String TB_YEAR = "year";
    private static final String TB_MONTH = "month";
    private static final String TB_SUPPLY = "supply";
    private static final String TYPE_TEXT = "TEXT";
    private static final String TYPE_INTEGER = "INTEGER";
    private static final String TYPE_REAL = "REAL";
    private static final String TYPE_INTEGER_PK = "INTEGER PRIMARY KEY";

    public static String getDbName() {
        return DB_NAME;
    }

    public static String getTbUser() {
        return TB_USER;
    }

    public static String getTbYear() {
        return TB_YEAR;
    }

    public static String getTbMonth() {
        return TB_MONTH;
    }

    public static String getTbSupply() {
        return TB_SUPPLY;
    }

    public static String createTableUser(){
        String query = "CREATE TABLE " + getTbUser() + " (";
        query += "id" + ' ' + TYPE_INTEGER_PK + " AUTOINCREMENT DEFAULT 1, ";
        query += "name " + TYPE_TEXT + ", ";
        query += "car " + TYPE_TEXT + ", ";
        query += "car_year " + TYPE_INTEGER + ", ";
        query += "avg_consumption " +  TYPE_REAL + " )";
        return query;
    }

    public static String createTableYear(){
        String query = "CREATE TABLE " + getTbYear() + " (";
        query += "id"+ ' ' + TYPE_INTEGER_PK + " AUTOINCREMENT DEFAULT 1, ";
        query += "year " + TYPE_INTEGER + " )";
        return query;
    }

    public static String createTableMonth(){
        String query = "CREATE TABLE " + getTbMonth() + " (";
        query += "id" + ' ' + TYPE_INTEGER_PK + " AUTOINCREMENT DEFAULT 1, ";
        query += "name " + TYPE_TEXT + ", ";
        query += "number " + TYPE_INTEGER + ", ";
        query += "year_id " + TYPE_INTEGER + ", ";
        query += "FOREIGN KEY(year_id) REFERENCES " + getTbYear() +"(id) )";
        return query;
    }

    public static String createTableSupply(){
        String query = "CREATE TABLE " + getTbSupply() + " (";
        query += "id" + ' ' + TYPE_INTEGER_PK + " AUTOINCREMENT DEFAULT 1, ";
        query += "value " + TYPE_REAL + ", ";
        query += "liters " + TYPE_REAL + ", ";
        query += "date  DATETIME DEFAULT CURRENT_DATE, ";
        //query += "date " + TYPE_TEXT + ", ";
        query += "gas_station " + TYPE_TEXT + ", ";
        query += "month_id " + TYPE_INTEGER + ", ";
        query += "FOREIGN KEY(month_id) REFERENCES " + getTbMonth() +"(id) )";
        return query;
    }
}