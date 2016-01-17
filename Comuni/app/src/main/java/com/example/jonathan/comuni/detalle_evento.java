package com.example.jonathan.comuni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class detalle_evento extends AppCompatActivity {

    TextView titulo,descripcion,direccion, hora_inicio, hora_termino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);
        titulo = (TextView) findViewById(R.id.titulo_evento_detallada);
        descripcion = (TextView) findViewById(R.id.descripcion_evento_detallada);
        direccion = (TextView) findViewById(R.id.direccion_evento_detallada);
        hora_inicio = (TextView) findViewById(R.id.hora_inicio_evento_detallado);
        hora_termino = (TextView) findViewById(R.id.hora_termino_evento_detallado);

        //JsonHandler jh = new JsonHandler();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        titulo.setText("titulo");

        //descripcion = (TextView) findViewById(R.id.descripcion);
        //comuna = (TextView) findViewById(R.id.comuna);
        //direccion = (TextView) findViewById(R.id.direccion);
        //popularidad = (TextView) findViewById(R.id.fecha);

        //titulo.setText(getIntent().getStringExtra("titulo"));
        //descripcion.setText(getIntent().getStringExtra("descripcion"));
        //comuna.setText(getIntent().getStringExtra("comuna"));
        //direccion.setText(getIntent().getStringExtra("direccion"));
        //pularidad.setText(getIntent().getStringExtra("popularidad"));


    }
}
