package com.example.jonathan.comuni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class detalle_evento extends AppCompatActivity {

    TextView descripcion,titulo,comuna, direccion, popularidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titulo = (TextView) findViewById(R.id.titulo);
        descripcion = (TextView) findViewById(R.id.descripcion);
        comuna = (TextView) findViewById(R.id.comuna);
        direccion = (TextView) findViewById(R.id.direccion);
        popularidad = (TextView) findViewById(R.id.fecha);

        //titulo.setText(getIntent().getStringExtra("titulo"));
        //descripcion.setText(getIntent().getStringExtra("descripcion"));
        //comuna.setText(getIntent().getStringExtra("comuna"));
        //direccion.setText(getIntent().getStringExtra("direccion"));
        //pularidad.setText(getIntent().getStringExtra("popularidad"));

        setContentView(R.layout.activity_detalle_evento);
    }
}
