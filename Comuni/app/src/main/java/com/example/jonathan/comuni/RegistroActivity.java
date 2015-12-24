package com.example.jonathan.comuni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        spinner = (Spinner) findViewById(R.id.spin_comuna);

        spinner.setOnItemSelectedListener(this);

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
}
