package com.company.alves.gastracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.alves.gastracker.DAO.UserDAO;
import com.company.alves.gastracker.DAO.YearDAO;
import com.company.alves.gastracker.Model.User;
import com.company.alves.gastracker.Model.Year;

public class IndexActivity extends AppCompatActivity {
    YearDAO yearDAO;
    private EditText edtUser;
    private EditText edtName;
    private EditText edtCar;
    private EditText edtYear;
    private EditText edtAvg;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        edtYear = (EditText) findViewById(R.id.edtYear);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Year year = new Year();
                year.setYear(Integer.valueOf(edtYear.getText().toString()));
                yearDAO = new YearDAO(getApplicationContext());
                if(yearDAO.addNEditYear(year)){
                    Toast.makeText(getApplication(), "Usuário criado com sucesso", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplication(), "Ocorreu um erro, tente novamente mais tarde", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
