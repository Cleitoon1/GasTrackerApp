package com.company.alves.gastracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.company.alves.gastracker.DAO.UserDAO;

public class GeneralList extends AppCompatActivity {

    private Button btnEdit;
    private Button btnReport;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_list);
        userDAO = UserDAO.getInstance(getApplicationContext());
        if (userDAO.countUsers() < 1) {
            Intent userIntent = new Intent(GeneralList.this, UserActivity.class);
            startActivity(userIntent);
            finish();
        }

        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnReport = (Button) findViewById(R.id.mensalBtn);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent userIntent = new Intent(GeneralList.this, UserActivity.class);
                startActivity(userIntent);
                finish();
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailedIntent = new Intent(GeneralList.this, DetailedList.class);
                startActivity(detailedIntent);
                finish();
            }
        });
    }
}
