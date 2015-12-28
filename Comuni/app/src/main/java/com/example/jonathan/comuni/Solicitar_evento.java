package com.example.jonathan.comuni;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import android.content.Context;
import android.widget.Toast;

import java.util.Calendar;


public class Solicitar_evento extends AppCompatActivity implements View.OnClickListener {


    private BroadcastReceiver br = null;
    private final String URL_POST = "http://192.168.0.11:8080/COMUNIbackend/eventos";

    TextView date;
    ImageButton boton_calendario;
    int mYear;
    int mMonth;
    int mDay;
    static final int DATE_DIALOG_ID = 0;

    int id_comuna = 18;
    Button aceptar;

    EditText tituloEnter, direccionEnter, descripcionEnter, hora_inicioEnter, hora_terminoEnter, imagenEnter;
    String titulo, direccion, descripcion, hora_inicio, hora_termino, imagen, fecha_inicio, fecha_termino;

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_solicitar_evento);

        tituloEnter = (EditText) findViewById(R.id.edit_titulo_evento);
        direccionEnter = (EditText) findViewById(R.id.edit_direccion_evento);
        imagenEnter = (EditText) findViewById(R.id.edit_foto_evento);
        descripcionEnter = (EditText) findViewById(R.id.edit_descripcion_evento);
        hora_inicioEnter = (EditText) findViewById(R.id.hora_inicio);
        hora_terminoEnter = (EditText) findViewById(R.id.hora_termino);


        aceptar = (Button) findViewById(R.id.boton_aceptar);

        // obtener comuna de usario


        date = (TextView) findViewById(R.id.fecha_registro_evento);
        boton_calendario = (ImageButton) findViewById(R.id.imagen_fecha);

        boton_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mMonth = mMonth + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

        aceptar.setOnClickListener(this);
    }

    public void onClick(View v) {
        titulo = tituloEnter.getText().toString();
        descripcion = descripcionEnter.getText().toString();
        imagen = imagenEnter.getText().toString();
        hora_termino = hora_inicioEnter.getText().toString();
        hora_termino = hora_terminoEnter.getText().toString();
        direccion = direccionEnter.getText().toString();

        int estado = 0;
        int popularidad = 0;
        double longitud = -70.704141, latitud = -33.464437;
        fecha_inicio = String.valueOf(mYear) + "-" + String.valueOf(mMonth) + "-" + String.valueOf(mDay) + " " + hora_inicio;

        fecha_termino = String.valueOf(mYear) + "-" + String.valueOf(mMonth) + "-" + String.valueOf(mDay) + " " + hora_termino;
        // Log.i("Post",email+ nombre+ apellido + pass + rep_pass + telefono + direccion+ id_comuna+" "+fechaNacimiento+" esto imprimi ");


        //   **** DATOS OBLIGATORIOS                DATOS OPCIONALES
        // titulo                                       TELEFONO
        // descripcion                                        DIRECCION
        // direccion
        // hora de inicio
        // id_comuna                                                                          ****//
        if(titulo.compareTo("") == 0 || descripcion.compareTo("") == 0 || direccion.compareTo("") == 0 || hora_inicio.compareTo("") == 0 || hora_termino.compareTo("") == 0){
            Toast.makeText(getApplicationContext(),"Debe llenar los campos", Toast.LENGTH_LONG).show();
        }else{

            IntentFilter intentFilter = new IntentFilter("httpPostResponse");

            this.registerReceiver(br, intentFilter);
            systemUtilities su = new systemUtilities(this.getApplicationContext());
            if (su.isNetworkAvailable()) {

                new HttpPostEvento(this.getApplicationContext(),
                        "{\"decripcion\":\"" + descripcion + "\", \"direccion\":\"" + direccion + "\", \"estado\":\"" + estado + "\", \"eventoComunaId\":\"" + id_comuna + "\", \"horaInicio\":\"" + fecha_inicio
                                + "\", \"horaTermino\":\"" + fecha_termino + "\", \"latitud\":\"" + latitud + "\", \"f\":\"" + longitud + "\", \"pathImagen\":\"" + imagen+"\", \"popularidad\":\""+popularidad+"\", \"titulo\":\""+titulo+"\"}", this).execute(URL_POST);
            } else {
                Log.e("TBD_TAG", "error de red");
                Toast.makeText(this.getApplicationContext(), "Error de conexión , intenta de nuevo o más tarde", Toast.LENGTH_LONG).show();
            }
        }
    }


     private void updateDisplay(){
        date.setText(new StringBuilder().append(mYear).append("-").append(mMonth).append("-").append(mDay));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ver_eventos:
                Intent ver_evento = new Intent(Solicitar_evento.this, Ver_eventos.class);
                startActivity(ver_evento);
                break;
            case R.id.notificaciones:
                Toast.makeText(getApplicationContext(), "Aprete notificaciones", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editar_perfil:
                Intent editar_perfil = new Intent(Solicitar_evento.this, Editar_perfil.class);
                startActivity(editar_perfil);
                break;
            case R.id.solicitud:
                Intent solicitud_evento = getIntent();
                finish();
                startActivity(solicitud_evento);
                break;
            case R.id.salir:
                finish();
                System.exit(0);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    public void agregado(String s){
        Log.i("TBD_TAG","data:"+s);
        if(s.compareTo("No Content")==0){

            Log.i("TBD_TAG", "Añadido");
            Toast.makeText(this,
                    "Ha creado un evento exitosamente", Toast.LENGTH_LONG).show();
            InputMethodManager mgr = (InputMethodManager) this
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(this.direccionEnter.getWindowToken(), 0);

        }
    }

}
