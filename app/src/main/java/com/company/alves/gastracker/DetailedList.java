package com.company.alves.gastracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    private Month month;
    private Year year;
    private List<Supply> supplys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_list);
        //Instância os DAOs e o objetos da tela
        supplyDAO = SupplyDAO.getInstance(getApplicationContext());
        monthDAO =  MonthDAO.getInstance(getApplicationContext());
        yearDAO = YearDAO.getInstance(getApplicationContext());
        listView = (ListView) findViewById(R.id.supplyList);
        Button btnRight = (Button) findViewById(R.id.arrowRight);
        Button btnLeft = (Button) findViewById(R.id.arrowLeft);
        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        final TextView monthTxt = (TextView) findViewById(R.id.monthTxt);
        //Busca o mes atual, colocar o nome na tela e chama a função de preencher o listView usando o id do mes
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        mes = calendar.get(Calendar.MONTH) + 1;
        if(mes <= 12 && mes > 1)
            year = yearDAO.getYearByNumber(calendar.get(Calendar.YEAR));
        month = monthDAO.getMonthByDate(mes, year.getId());

        supplys = supplyDAO.getSupplyByMonth(month.getId());
        if(supplys.size() > 0) {
            ArrayAdapter<Supply> supplyAdapter = new ArrayAdapter<Supply>(this, android.R.layout.simple_list_item_1, supplys);
            listView.setAdapter(supplyAdapter);
        }
        monthTxt.setText(month.getName());
        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if((mes - 1 ) >= 1) {
                    mes = mes - 1;
                    supplys = new ArrayList<Supply>();
                    ArrayAdapter<Supply> supplyAdapter = new ArrayAdapter<Supply>(DetailedList.this, android.R.layout.simple_list_item_1, supplys);
                    listView.setAdapter(supplyAdapter);
                    month = monthDAO.getMonthByDate(mes, year.getId());
                    monthTxt.setText(month.getName());
                    supplys = supplyDAO.getSupplyByMonth(month.getId());
                    if(supplys.size() > 0) {
                        supplyAdapter = new ArrayAdapter<Supply>(DetailedList.this, android.R.layout.simple_list_item_1, supplys);
                        listView.setAdapter(supplyAdapter);
                    }
                }
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if((mes + 1) <= 12) {
                    mes = mes + 1;
                    supplys = new ArrayList<Supply>();
                    ArrayAdapter<Supply> supplyAdapter = new ArrayAdapter<Supply>(DetailedList.this, android.R.layout.simple_list_item_1, supplys);
                    listView.setAdapter(supplyAdapter);
                    month = monthDAO.getMonthByDate(mes, year.getId());
                    monthTxt.setText(month.getName());
                    supplys = supplyDAO.getSupplyByMonth(month.getId());
                    if(supplys.size() > 0) {
                        supplyAdapter = new ArrayAdapter<Supply>(DetailedList.this, android.R.layout.simple_list_item_1, supplys);
                        listView.setAdapter(supplyAdapter);
                    }
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentGas = new Intent(DetailedList.this, RegisterGas.class);
                intentGas.putExtra("monthId", month.getId());
                DetailedList.this.startActivity(intentGas);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentUser = new Intent(DetailedList.this, UserActivity.class);
                DetailedList.this.startActivity(intentUser);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                Intent intentGas = new Intent(DetailedList.this, RegisterGas.class);
                intentGas.putExtra("supplyId", supplys.get(myItemInt).getId());
                DetailedList.this.startActivity(intentGas);
            }
        });
    }

}
