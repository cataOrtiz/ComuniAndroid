package com.example.jonathan.comuni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText usuario,contraseña;
    Button boton_registro,boton_inicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.usuario);
        contraseña = (EditText) findViewById(R.id.contraseña);
        boton_registro = (Button) findViewById(R.id.registrarse);
        boton_inicio = (Button) findViewById(R.id.inicio_sesion);
        boton_registro.setOnClickListener(this);
        boton_inicio.setOnClickListener(this);
    }


     @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inicio_sesion:
                Intent ver_evento = new Intent(MainActivity.this,Ver_eventos.class);
                startActivity(ver_evento);
                break;
            case R.id.registrarse:
                Intent registro = new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(registro);
                break;
            //case R.id.boton:
            //    Toast.makeText(getApplicationContext(), "Esto es un Toast", Toast.LENGTH_SHORT).show();
            //    String dato = editText.getText().toString();
            //    texto.setText(dato);
            //    break;
            //case R.id.botonN:
            //    texto.setText("Boton 2!!!");
            //    break;
            //case R.id.button:
            //    String dato1 = editText.getText().toString();
            //    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            //    intent.putExtra("DATO",dato1);
            //    startActivity(intent);
            //    break;
        }

    }


}
