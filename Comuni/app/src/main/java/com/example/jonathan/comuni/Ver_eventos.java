package com.example.jonathan.comuni;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.util.Log;
import android.widget.Toast;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.List;


public class Ver_eventos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private BroadcastReceiver br = null;
    private final String URL_GET = "http://192.168.0.11:8080/COMUNIbackend/eventos";
    private String jsonData;
    ListView evento;


    @Override
    protected void onResume() {
        super.onResume();

        setContentView(R.layout.activity_ver_eventos);

        evento = (ListView) findViewById(R.id.lista_eventos);


        IntentFilter intentFilter = new IntentFilter("httpData");
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                JsonHandler jh = new JsonHandler();
                String[] eventos = jh.getEventos(intent.getStringExtra("data"));
                ArrayAdapter<String> DataEventos = new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1, eventos);
                evento.setAdapter(DataEventos);
            }
        };
        this.registerReceiver(br, intentFilter);
        systemUtilities su = new systemUtilities(this.getApplicationContext());

        //try {
        if (su.isNetworkAvailable()) {
            new HttpGet(this.getApplicationContext()).execute(URL_GET);
            //Log.e("err en el if", this.getClass().toString() + " " + toString());
        }
        else{
            Toast.makeText(this, "no hay conexion a internet", Toast.LENGTH_SHORT).show();
        }
       /*
        ArrayList<String> eventos = new ArrayList<String>();
        eventos.add("Bingo");
        eventos.add("Tallarinata");
        eventos.add("Cicletada");
        eventos.add("Lunes de cafe");
        eventos.add("Martes de chorillana");
        eventos.add("Viernes de TBD");
        eventos.add("Taller de JavaEE");
        eventos.add("Whatsapp avanzado con Marin");
        eventos.add("Taller de daily meeting");

       */
        evento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Start your Activity according to the item just clicked.

                 String item = parent.getItemAtPosition(position).toString();

                // Toast.makeText(getApplicationContext(),"Selecciono: "+item,Toast.LENGTH_LONG).show(
                //JsonHandler jh = new JsonHandler();
                //String[] datos_detalle = jh.getDetalle(this.toString(jsonData), position);
                Intent i = new Intent(Ver_eventos.this, detalle_evento.class);
                //i.putExtra("titulo", datos_detalle[0]);
                //i.putExtra("descripcion", datos_detalle[1]);
                //i.putExtra("comuna", datos_detalle[2]);
                //i.putExtra("direccion", datos_detalle[3]);
                //i.putExtra("popularidad", datos_detalle[4]);
                startActivity(i);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ver_eventos:
                Intent ver_evento = getIntent();
                finish();
                startActivity(ver_evento);
                break;
            case R.id.notificaciones:
                Toast.makeText(getApplicationContext(), "Aprete notificaciones", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editar_perfil:
                Intent editar_perfil = new Intent(Ver_eventos.this, Editar_perfil.class);
                startActivity(editar_perfil);
                break;
            case R.id.solicitud:
                Intent solicitar_evento = new Intent(Ver_eventos.this, Solicitar_evento.class);
                startActivity(solicitar_evento);
                break;
            case R.id.salir:
                finish();
                System.exit(0);
                break;

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(getApplicationContext(),"Selecciono: "+item,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
