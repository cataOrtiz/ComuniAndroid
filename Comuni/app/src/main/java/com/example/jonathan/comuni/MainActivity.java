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
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import java.lang.String;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BroadcastReceiver br_1 = null;
    boolean valida = false;
    private final String URL_GET = "http://192.168.0.11:8080/COMUNIbackend/usuarios";

    EditText email,contraseña;
    Button boton_registro,boton_inicio;
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.edit_emailMain);
        contraseña = (EditText) findViewById(R.id.contraseña);
        boton_registro = (Button) findViewById(R.id.registrarse);
        boton_inicio = (Button) findViewById(R.id.inicio_sesion);
        boton_registro.setOnClickListener(this);
        boton_inicio.setOnClickListener(this);

    }


     @Override
    public void onClick(View v) {
         final boolean[] valido = {false};
        switch (v.getId()){
            case R.id.inicio_sesion:
//   ESTAS DOS LINEAS SE DEBEN COMENTAR PARA PROBAR QUE FUNCIONA EL LOGIN PERO TIRA UN ERROR DESPUES
                  Intent ver_evento = new Intent(MainActivity.this, Ver_eventos.class);
                  startActivity(ver_evento);

//      SE DEBEN DESCOMENTAR LAS SIGUIENTES LINEAS
/*
                IntentFilter intentFilter_1 = new IntentFilter("httpData_1");
                br_1 = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {

                        JsonHandler jh = new JsonHandler();

                        String contraseñaPorRevisar = contraseña.getText().toString();
                        String emailPorRevisar = email.getText().toString();
                        boolean valido=false;
                        String [] datos_usuarios= jh.getUsuarios(intent.getStringExtra("data"));
                        valido = validar(emailPorRevisar, contraseñaPorRevisar, datos_usuarios);

                        if( contraseñaPorRevisar.length()!=0 || emailPorRevisar.length()!=0 ) {
                            if (valido == true) {

                                  Intent ver_evento = new Intent(MainActivity.this, Ver_eventos.class);
                                  startActivity(ver_evento);
                            } else {
                                Toast.makeText(getApplicationContext(), "No se ha podido encontrar el usario, por favor ingrese sus datos nuevamente", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "No ha iniciado sesion, por favor ingrese sus datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                this.registerReceiver(br_1, intentFilter_1);
                systemUtilities su = new systemUtilities(this.getApplicationContext());
                    if (su.isNetworkAvailable()) {
                        new HttpGet(this.getApplicationContext()).execute(URL_GET);
                        Log.e("err en el if", this.getClass().toString() + " " + toString());
                    } else {
                        Toast.makeText(this, "no hay conexion a internet", Toast.LENGTH_SHORT).show();
                    }

                InputMethodManager mgr = (InputMethodManager) this
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(this.contraseña.getWindowToken(), 0);

*/

                    break;
            case R.id.registrarse:
                Intent registro = new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(registro);
                break;

        }
    }

    public boolean validar(String emailPorRevisar, String contraseñaPorRevisar, String[] datos_usuarios){

        String datoPorRevisar = emailPorRevisar+"+"+contraseñaPorRevisar;
        Log.i("info","datosPorRevisar: "+datoPorRevisar);
        for(int i=0; i < datos_usuarios.length; i++ ){
        Log.i("info","datos_usuarios: "+datos_usuarios[i]);
            if(datoPorRevisar.compareTo(datos_usuarios[i])==0){
                Log.i("info", "true");

                return true;
            }
        }
        return false;
    }

}
