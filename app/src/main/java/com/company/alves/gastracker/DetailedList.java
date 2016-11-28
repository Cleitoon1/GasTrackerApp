package com.company.alves.gastracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.company.alves.gastracker.DAO.MonthDAO;
import com.company.alves.gastracker.DAO.SupplyDAO;
import com.company.alves.gastracker.DAO.YearDAO;
import com.company.alves.gastracker.Model.Month;
import com.company.alves.gastracker.Model.Supply;
import com.company.alves.gastracker.Model.Year;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DetailedList extends AppCompatActivity {
    private Button btnRight;
    private Button btnLeft;
    private Button btnEdit;
    private Button btnBack;
    private ListView listView;
    private EditText edtMonth;
    private SupplyDAO supplyDAO;
    private MonthDAO monthDAO;
    private YearDAO yearDAO;
    private TextView monthTxt;
    private int mes;
    private int ano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_list);
        supplyDAO = SupplyDAO.getInstance(getApplicationContext());
        monthDAO =  MonthDAO.getInstance(getApplicationContext());
        yearDAO = YearDAO.getInstance(getApplicationContext());
        final ListView listView = (ListView) findViewById(R.id.listView);
        Button btnRight = (Button) findViewById(R.id.arrowRight);
        Button btnLeft = (Button) findViewById(R.id.arrowLeft);
        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        final TextView monthTxt = (TextView) findViewById(R.id.monthTxt);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        mes = calendar.get(Calendar.MONTH) + 1;
        List<Month> teste = monthDAO.getMonths(0);
        Year y = yearDAO.getYearByNumber(calendar.get(Calendar.YEAR));
        Month month = monthDAO.getMonthByDate(mes, y.getYear());
        monthTxt.setText(month.getName());
        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if((mes - 1 ) >= 1) {
                    mes = mes - 1;
                    Month month = monthDAO.getMonthByDate(mes, 0);
                    monthTxt.setText(month.getName());
                }
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if((mes + 1) <= 12) {
                    mes = mes + 1;
                    Month month = monthDAO.getMonthByDate(mes, 0);
                    monthTxt.setText(month.getName());
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentGas = new Intent(DetailedList.this, RegisterGas.class);
                intentGas.putExtra("monthId", 0); //Optional parameters
                intentGas.putExtra("supplyId", 0);
                DetailedList.this.startActivity(intentGas);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentUser = new Intent(DetailedList.this, UserActivity.class);
                DetailedList.this.startActivity(intentUser);
            }
        });
    }

    public void preencherListView(int ano, int mes){
        List<Supply> supplys = new ArrayList<>();
        ArrayAdapter<Supply> supplysAdapter = new ArrayAdapter<Supply>(this, android.R.layout.activity_list_item, supplys);
        listView.setAdapter(supplysAdapter);
    }
}
