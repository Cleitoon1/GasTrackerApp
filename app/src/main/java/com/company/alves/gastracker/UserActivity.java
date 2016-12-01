package com.company.alves.gastracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.alves.gastracker.DAO.UserDAO;
import com.company.alves.gastracker.Model.User;

public class UserActivity extends AppCompatActivity {
    private EditText edtId;
    private EditText edtName;
    private EditText edtCar;
    private EditText edtYear;
    private EditText edtAvg;
    private Button btnSave;
    private UserDAO userDAO;
    //http://professormarcomaddo.blogspot.com.br/2015/05/curso-android-aula-10-crud-sqlite-insert.html
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userDAO = UserDAO.getInstance(getApplicationContext());

        //passando os valores para a variavel
        edtId = (EditText) findViewById(R.id.edtId);
        edtName = (EditText) findViewById(R.id.edtName);
        edtCar = (EditText) findViewById(R.id.edtCar);
        edtYear = (EditText) findViewById(R.id.edtYear);
        edtAvg = (EditText) findViewById(R.id.edtAvg);
        btnSave = (Button) findViewById(R.id.btnNext);

        if(userDAO.countUsers() > 0) {

            User usr = userDAO.getUser();
            Log.d("Id", "Id: " + usr.getId());
            edtId.setText(String.valueOf(usr.getId()));
            edtName.setText(usr.getName());
            edtCar.setText(usr.getCar());
            edtYear.setText(String.valueOf(usr.getCarYear()));
            edtAvg.setText(String.valueOf(usr.getAvgConsumption()));
        }
        //pega o on click
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User user = new User();
                if(edtId.getText().toString().length() > 0 && edtId.getText().toString() != null)
                    user.setId(Integer.valueOf(edtId.getText().toString()));
                user.setName(edtName.getText().toString());
                user.setCar(edtCar.getText().toString());
                if(edtYear.getText().toString() != "" && edtYear.getText().toString() != null)
                    user.setCarYear(Integer.valueOf(edtYear.getText().toString()));
                if(edtAvg.getText().toString() != "" && edtAvg.getText().toString() != null)
                user.setAvgConsumption(Double.valueOf(edtAvg.getText().toString()));
                userDAO = UserDAO.getInstance(getApplicationContext());
                if(userDAO.addNEditUser(user)){
                    Intent dashboard = new Intent(UserActivity.this, GeneralList.class);
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