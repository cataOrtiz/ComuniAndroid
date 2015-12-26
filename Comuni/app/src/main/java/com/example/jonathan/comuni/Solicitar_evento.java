package com.example.jonathan.comuni;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Solicitar_evento extends AppCompatActivity {


    TextView date;
    ImageButton boton_calendario;
    int mYear;
    int mMonth;
    int mDay;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_evento);

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
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();
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




}
