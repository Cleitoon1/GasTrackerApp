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
import com.company.alves.gastracker.Model.Supply;

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
    private ListView listView;
    private EditText edtMonth;
    private SupplyDAO supplyDAO;
    private TextView monthTxt;
    private List<String> listMonths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_list);
        supplyDAO = SupplyDAO.getInstance(getApplicationContext());
        listMonths = new ArrayList<>();
        listMonths.addAll(Arrays.asList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"));
        final ListView listView = (ListView) findViewById(R.id.listView);
        Button btnRight = (Button) findViewById(R.id.arrowRight);
        Button btnLeft = (Button) findViewById(R.id.arrowLeft);
        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        final TextView monthTxt = (TextView) findViewById(R.id.monthTxt);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int mes = calendar.get(Calendar.MONTH);
        monthTxt.setText(listMonths.get(mes));

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(listMonths.lastIndexOf(monthTxt.getText()) > 0)
                    monthTxt.setText(listMonths.get((listMonths.lastIndexOf(monthTxt.getText())) - 1));
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(listMonths.lastIndexOf(monthTxt.getText()) < 11)
                    monthTxt.setText(listMonths.get((listMonths.lastIndexOf(monthTxt.getText())) + 1));
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentGas = new Intent(DetailedList.this, RegisterGas.class);
                intentGas.putExtra("supplyId", 1); //Optional parameters
                DetailedList.this.startActivity(intentGas);

            }
        });
    }

    public void preencherListView(int ano, int mes){
        List<Supply> supplys = new ArrayList<>();
        ArrayAdapter<Supply> supplysAdapter = new ArrayAdapter<Supply>(this, android.R.layout.activity_list_item, supplys);
        listView.setAdapter(supplysAdapter);
    }
}
