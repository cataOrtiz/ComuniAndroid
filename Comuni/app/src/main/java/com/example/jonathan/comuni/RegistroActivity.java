package com.example.jonathan.comuni;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.inputmethod.InputMethodManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import android.widget.Toast;
import android.util.Log;
public class RegistroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private BroadcastReceiver br = null;
    private final String URL_POST = "http://192.168.0.11:8080/COMUNIbackend/usuarios";
    Spinner spinner;
    TextView date;
    ImageButton boton_calendario;
    Button aceptar;
    EditText emailEnter, nombreEnter, apellidoEnter, passEnter, repPassEnter, telefonoEnter, direccionEnter;

    int mYear;
    int mMonth;
    int mDay;
    static final int DATE_DIALOG_ID = 0;
    int id_comuna;
    String email, nombre, apellido, pass, rep_pass, telefono, direccion, fechaNacimiento;
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_registro);

        emailEnter = (EditText)findViewById(R.id.edit_email);
        nombreEnter = (EditText)findViewById(R.id.edit_nombre);
        apellidoEnter = (EditText)findViewById(R.id.edit_apellido);
        passEnter = (EditText)findViewById(R.id.edit_contraseña);
        repPassEnter = (EditText)findViewById(R.id.edit_repetir_contraseña);
        telefonoEnter = (EditText)findViewById(R.id.edit_telefono);
        direccionEnter = (EditText)findViewById(R.id.edit_direccion);

        spinner = (Spinner) findViewById(R.id.spin_comuna);
        date = (TextView) findViewById(R.id.fecha_registro);
        boton_calendario = (ImageButton) findViewById(R.id.imagen_nacimiento);
        aceptar = (Button) findViewById(R.id.boton_aceptar);

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
        comunas.add("Pedro Aguirre Cerda");
        comunas.add("Peñalolén");
        comunas.add("Providencia");
        comunas.add("Pudahuel");
        comunas.add("Quilicura");
        comunas.add("Quinta Normal");
        comunas.add("Recoleta");
        comunas.add("Renca");
        comunas.add("San Miguel");
        comunas.add("San Joaquín");
        comunas.add("San Ramón");
        comunas.add("Santiago");
        comunas.add("Vitacura");

        //comunas.add("Padre Hurtado");
        //comunas.add("Pirque");
        //comunas.add("Puente Alto");
        //comunas.add("San Bernardo");
        //comunas.add("San José de Maipo");


        ArrayAdapter<String> DataComuna = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,comunas);

        spinner.setAdapter(DataComuna);

        aceptar.setOnClickListener(this);


    }

    public void onClick(View v) {
        // Aqui tiene que obtener los datos para ingresar los datos en el formulario
        Log.i("error","no llego aqui");
        email = emailEnter.getText().toString();
        nombre = nombreEnter.getText().toString();
        apellido = apellidoEnter.getText().toString();
        pass = passEnter.getText().toString();
        rep_pass = repPassEnter.getText().toString();
        telefono = telefonoEnter.getText().toString();
        direccion = direccionEnter.getText().toString();
    int tipo = 0;
        fechaNacimiento = String.valueOf(mYear)+"-"+String.valueOf(mMonth)+"-"+String.valueOf(mDay);
        // Log.i("Post",email+ nombre+ apellido + pass + rep_pass + telefono + direccion+ id_comuna+" "+fechaNacimiento+" esto imprimi ");


        //   **** DATOS OBLIGATORIOS                DATOS OPCIONALES
        // NOMBRE                                       TELEFONO
        // EMAIL                                        DIRECCION
        // APELLIDO
        // CONTRASEÑA
        //                                                                   ****//
        if(nombre.compareTo("")==0||nombre.compareTo("")==0 ||nombre.compareTo("")==0){
            Toast.makeText(getApplicationContext(),"Debe llenar los campos", Toast.LENGTH_LONG).show();
            Log.i("ERROR_USER", "DE");
        }else{


            Log.i("TDB_TAG", "EnviarPost(" + email + "," + nombre + "," + apellido + ");");
            IntentFilter intentFilter = new IntentFilter("httpPostResponse");

            this.registerReceiver(br, intentFilter);
            systemUtilities su = new systemUtilities(this.getApplicationContext());
            if (su.isNetworkAvailable()) {
                Log.i("TBD_TAG","Enviando Nuevo Actor");
                new HttpPost(this.getApplicationContext(),
                        "{\"apellido\":\""+apellido+"\", \"direccion\":\""+direccion+"\", \"email\":\""+email+"\", \"fechaNac\":\""+fechaNacimiento+"\", \"nombre\":\""+nombre
                                +"\", \"password\":\""+pass+"\", \"telefono\":\""+telefono+"\", \"tipo\":\""+tipo+"\", \"userIdComuna\":\""+id_comuna+"\"}", this).execute(URL_POST);
            }else{
                Log.e("TBD_TAG", "error de red");
                Toast.makeText(this.getApplicationContext(),"Error de conexión , intenta de nuevo o más tarde",Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(getApplicationContext(),"Selecciono: "+item + position,Toast.LENGTH_LONG).show();
        id_comuna = position+1;
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void updateDisplay(){
        date.setText(new StringBuilder().append(mYear).append("-").append(mMonth ).append("-").append(mDay));
    }

    private DatePickerDialog.OnDateSetListener DateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear+1;
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
    public void agregado(String s){
        Log.i("TBD_TAG","data:"+s);
        if(s.compareTo("No Content")==0){

            Log.i("TBD_TAG", "Añadido");
            Toast.makeText(this,
                    "Actor añadido exitosamente", Toast.LENGTH_LONG).show();
            InputMethodManager mgr = (InputMethodManager) this
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(this.direccionEnter.getWindowToken(), 0);

        }
    }
}
