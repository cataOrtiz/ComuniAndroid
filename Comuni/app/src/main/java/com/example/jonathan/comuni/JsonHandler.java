package com.example.jonathan.comuni;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Casa on 23-12-2015.
 */
public class JsonHandler {

    public String[] getEventos(String eventos) {
        try {
            JSONArray ja = new JSONArray(eventos);
            String[] result = new String[ja.length()];
            String evento;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                evento = " " + row.getString("titulo");
                result[i] = evento;
            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }
    public String[] getDetalle(String actors,int pos){
        try {
            JSONArray ja = new JSONArray(actors);
            JSONObject row = ja.getJSONObject(pos);
            String[] result = new String[5];
            result[0]=row.getString("titulo");
            result[1]=row.getString("descripcion");
            result[2]=row.getString("nombreComuna");
            result[3]=row.getString("direccion");
            result[4]=row.getString("horaInicio");
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }
    public String[] getUsuarios(String usuarios) {
        try {
            JSONArray ja = new JSONArray(usuarios);
            String[] result = new String[ja.length()];
            String usuario;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                usuario = row.getString("email")+"+"+row.getString("password");
                result[i] = usuario;
            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }



}
