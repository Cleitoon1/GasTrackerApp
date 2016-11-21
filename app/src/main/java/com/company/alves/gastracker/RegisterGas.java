package com.company.alves.gastracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.alves.gastracker.DAO.MonthDAO;
import com.company.alves.gastracker.DAO.SupplyDAO;
import com.company.alves.gastracker.Model.Supply;

import java.sql.Date;

public class RegisterGas extends AppCompatActivity {
    SupplyDAO supplyDAO;
    private EditText edtId;
    private EditText edtValor;
    private EditText edtLitro;
    private EditText edtStation;
    private EditText edtDate;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gas);

        supplyDAO = SupplyDAO.getInstance(getApplicationContext());
        edtId = (EditText) findViewById(R.id.supId);
        edtValor = (EditText) findViewById(R.id.edtValor);
        edtLitro = (EditText) findViewById(R.id.edtLitro);
        edtStation = (EditText) findViewById(R.id.edtStation);
        edtDate = (EditText) findViewById(R.id.edtValor);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SupplyDAO supplyDAO = SupplyDAO.getInstance(getApplicationContext());
                MonthDAO monthDAO =  MonthDAO.getInstance(getApplicationContext());

                Supply supply = new Supply();
                supply.setId(Integer.valueOf(edtId.getText().toString()));
                supply.setValue(Double.valueOf(edtValor.getText().toString()));
                supply.setLiters(Double.valueOf(edtLitro.getText().toString()));
                //supply.getDate(Date.valueOf(edtDate.getText().toString()));
                //supply.setIdMonth();
                if(supplyDAO.addNeditSupply(supply)){
                    Toast.makeText(getApplication(), "Registro criado com sucesso", Toast.LENGTH_LONG).show();
                    Intent dashboard = new Intent(RegisterGas.this, DetailedList.class);
                    startActivity(dashboard);
                    finish();
                }
                else {
                    Toast.makeText(getApplication(), "Ocorreu um erro, tente novamente mais tarde", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
