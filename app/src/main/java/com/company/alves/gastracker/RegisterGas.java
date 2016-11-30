package com.company.alves.gastracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private int monthId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gas);
        Bundle intent = getIntent().getExtras();
        int supplyId = 0;
        monthId = 0;

        supplyDAO = SupplyDAO.getInstance(getApplicationContext());
        edtId = (EditText) findViewById(R.id.supId);
        edtValor = (EditText) findViewById(R.id.edtValor);
        edtLitro = (EditText) findViewById(R.id.edtLitro);
        edtStation = (EditText) findViewById(R.id.edtStation);
        edtDate = (EditText) findViewById(R.id.edtValor);
        btnSave = (Button) findViewById(R.id.btnSave);

        if(intent.getInt("monthId") != 0)
            monthId = intent.getInt("monthId");
        if(intent.getInt("supplyId") != 0) {
            supplyId = intent.getInt("supplyId");
            Supply sup = supplyDAO.getSupply(supplyId);
            edtId.setText(String.valueOf(sup.getId()));
            edtStation.setText(sup.getGasStation());
            edtLitro.setText(String.valueOf(sup.getLiters()));
            edtValor.setText(String.valueOf(sup.getValue()));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SupplyDAO supplyDAO = SupplyDAO.getInstance(getApplicationContext());
                Supply supply = new Supply();
                supply.setGasStation(edtStation.getText().toString());
                if(edtId.getText().toString() != "" && edtId.getText().toString().length() > 0)
                    supply.setId(Integer.valueOf(edtId.getText().toString()));
                if(edtValor.getText().toString() != "" && Double.valueOf(edtValor.getText().toString()) > 0)
                    supply.setValue(Double.valueOf(edtValor.getText().toString()));
                if(edtLitro.getText().toString() != "" && Double.valueOf(edtLitro.getText().toString()) > 0)
                    supply.setLiters(Double.valueOf(edtLitro.getText().toString()));
                if(monthId != 0)
                    supply.setIdMonth(monthId);
                if(supplyDAO.addNeditSupply(supply)){
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
