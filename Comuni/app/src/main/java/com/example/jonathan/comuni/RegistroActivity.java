package com.example.jonathan.comuni;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    TextView date;
    ImageButton boton_calendario;
    int mYear;
    int mMonth;
    int mDay;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        spinner = (Spinner) findViewById(R.id.spin_comuna);
        date = (TextView) findViewById(R.id.fecha_registro);
        boton_calendario = (ImageButton) findViewById(R.id.imagen_nacimiento);

        spinner.setOnItemSelectedListener(this);
        boton_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

        ArrayList<String> comunas= new ArrayList<String>();
        comunas.add("Cerrillos");
        comunas.add("Cerro Navia");
        comunas.add("Conchalí");
        comunas.add("El Bosque");
        comunas.add("Estación Central");
        comunas.add("Huechuraba");
        comunas.add("Independencia");
        comunas.add("La Cisterna");
        comunas.add("La Florida");
        comunas.add("La Granja");
        comunas.add("La Pintana");
        comunas.add("La Reina");
        comunas.add("Las Condes");
        comunas.add("Lo Barnechea");
        comunas.add("Lo Espejo");
        comunas.add("Lo Prado");
        comunas.add("Macul");
        comunas.add("Maipú");
        comunas.add("Ñuñoa");
        comunas.add("Padre Hurtado");
        comunas.add("Pedro Aguirre Cerda");
        comunas.add("Peñalolén");
        comunas.add("Pirque");
        comunas.add("Providencia");
        comunas.add("Pudahuel");
        comunas.add("Puente Alto");
        comunas.add("Quilicura");
        comunas.add("Recoleta");
        comunas.add("Renca");
        comunas.add("San Bernardo");
        comunas.add("San Joaquín");
        comunas.add("San José de Maipo");
        comunas.add("San Miguel");
        comunas.add("San Ramón");
        comunas.add("Santiago");
        comunas.add("Vitacura");

        ArrayAdapter<String> DataComuna = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,comunas);

        spinner.setAdapter(DataComuna);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void updateDisplay(){
        date.setText(new StringBuilder().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay));
    }

    private DatePickerDialog.OnDateSetListener DateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,DateSetListener,mYear,mMonth,mDay);
        }
        return null;
    }
}
