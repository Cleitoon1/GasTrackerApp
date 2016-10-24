package com.company.alves.gastracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.alves.gastracker.DAO.UserDAO;
import com.company.alves.gastracker.Model.User;

public class UserActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtCar;
    private EditText edtYear;
    private EditText edtAvg;
    private Button btnSave;

    //http://professormarcomaddo.blogspot.com.br/2015/05/curso-android-aula-10-crud-sqlite-insert.html
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        UserDAO userDAO = new UserDAO(getApplicationContext());
        if(userDAO.countUsers() > 0){
            Intent dashboard = new Intent(UserActivity.this, IndexActivity.class);
            startActivity(dashboard);
            finish();
        }
        //passando os valores para a variavel
        edtName = (EditText) findViewById(R.id.btnYear);
        edtCar = (EditText) findViewById(R.id.edtCar);
        edtYear = (EditText) findViewById(R.id.edtYear);
        edtAvg = (EditText) findViewById(R.id.edtAvg);
        btnSave = (Button) findViewById(R.id.btnNext);

        //pega o on click
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User user = new User();
                user.setName(edtName.getText().toString());
                user.setCar(edtCar.getText().toString());
                user.setCarYear(Integer.valueOf(edtYear.getText().toString()));
                user.setAvgConsumption(Double.valueOf(edtAvg.getText().toString()));
                UserDAO userDAO = new UserDAO(getApplicationContext());
                if(userDAO.addNEditUser(user)){
                    Toast.makeText(getApplication(), "Usuário criado com sucesso", Toast.LENGTH_LONG).show();
                    Intent dashboard = new Intent(UserActivity.this, IndexActivity.class);
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