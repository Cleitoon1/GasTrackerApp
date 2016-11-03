package com.company.alves.gastracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.company.alves.gastracker.DAO.UserDAO;

public class GeneralList extends AppCompatActivity {

    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_list);
        UserDAO userDAO = new UserDAO(getApplicationContext());
        if (userDAO.countUsers() < 1) {
            Intent userIntent = new Intent(GeneralList.this, UserActivity.class);
            startActivity(userIntent);
            finish();
        }

        btnEdit = (Button) findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent userIntent = new Intent(GeneralList.this, UserActivity.class);
                startActivity(userIntent);
                finish();
            }
        });
    }
}
